package com.practise.newsapp.presentation.screens.register.signup

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.FirebaseFirestoreException
import com.practise.newsapp.common.utils.CommonString
import com.practise.newsapp.common.viewmodel.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SignupViewModel @Inject constructor() : BaseViewModel() {
    var state by mutableStateOf(SignupContract.State())
    var effects = Channel<SignupContract.Effect>(Channel.UNLIMITED)

    suspend fun createNewUser(
        username: String,
        email: String,
        password: String,
        onResult: () -> Unit
    ) {
        try {
            apiState = apiState.copy(
                isLoading = true
            )
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()

            val userId = authResult.user?.uid
                ?: throw IllegalStateException("User created but UID is null")

            val userMap = hashMapOf(
                CommonString.USERNAME to username,
                CommonString.EMAIL to email
            )
            onResult()
            db.collection(CommonString.FIREBASE_DB_USERS).document(userId).set(userMap).await()

        } catch (e: FirebaseAuthException) {
            // Specific Firebase Auth exceptions
            Log.d("TAG", "Authentication error: ${e.message} (Error code: ${e.errorCode})")
        } catch (e: FirebaseFirestoreException) {
            Log.d("TAG", "ADatabase error: ${e.message}")
        } catch (e: Exception) {
            // Generic exceptions
            Log.d("TAG", "An unexpected error occurred: \${e.message")
        }
        finally {
            apiState = apiState.copy(
                isLoading = false
            )
        }
    }
}