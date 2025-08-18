package com.practise.newsapp.common.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.practise.newsapp.common.utils.ApiState
import com.practise.newsapp.common.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Response
import java.net.SocketTimeoutException

open class BaseViewModel: ViewModel() {

    var apiState by mutableStateOf(ApiState())
    private var apiJob: Job? = null
    val db = FirebaseFirestore.getInstance()
    var auth: FirebaseAuth = Firebase.auth

    protected fun <T> apiCallWithJob(
        key: String = Constants.EMPTY_STRING,
        api: suspend () -> Response<T>,
        onSuccess: suspend (result: T) -> Unit,
        onError: suspend (code: Int, message: String, errorBody: String?) -> Unit,
        beforeCall: (suspend () -> Unit)? = null,
        isAccessTokenRequired: Boolean = true,
        isLoadingRequired: Boolean = true,
        dismissLoaderOnCompletion: Boolean = true,
        apiJobCallback: ((Job) -> Unit)? = null,
    ) {
        if (isLoadingRequired) {
            apiState = apiState.copy(isLoading = true)
        }

        val job = viewModelScope.launch(Dispatchers.IO) {
            try {
                beforeCall?.invoke()

                // if your API requires token, fetch or validate it here
//                val accessToken = if (isAccessTokenRequired) getAccessToken() else ""

                val response = api()

                if (response.isSuccessful) {
                    response.body()?.let { body ->
                        if (dismissLoaderOnCompletion) {
                            apiState = apiState.copy(isLoading = false)
                        }
                        onSuccess(body)
                    } ?: run {
                        onError(response.code(), "Empty Response Body", null)
                    }
                } else {
                    onError(response.code(), response.message(), response.errorBody()?.string())
                }

            } catch (e: SocketTimeoutException) {
                apiState = apiState.copy(isLoading = false)
                onError(500, "Timeout", e.message)
            } catch (e: Exception) {
                apiState = apiState.copy(isLoading = false)
                onError(500, "Exception", e.message)
            }
        }
        apiJobCallback?.invoke(job)
    }

}