package com.practise.newsapp.presentation.screens.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.practise.newsapp.BuildConfig
import com.practise.newsapp.common.viewmodel.BaseViewModel
import com.practise.newsapp.domain.api.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MainRepository
) : BaseViewModel() {

    var state by mutableStateOf(HomeContract.state())
    private var searchJob: Job? = null

    fun getNews() {
        apiCallWithJob(
            api = {
                repository.getNews(
                    q = "cricket",
                    pageSize = "10",
                    fromDate = "2025-07-31",
                    apiKey = "81fa24457e154a7a844c37bdb5e1c168"
                )
            },
            onSuccess = { result ->
                state = state.copy(articles = result.articles ?: emptyList())
                Log.d("HomeViewModel", "✅ News loaded successfully")
            },
            onError = { code, message, body ->
                apiState = apiState.copy(
                    apiErrorMessage = "Error $code",
                    apiErrorDescription = body ?: message,
                    isApiSuccessful = false,
                    showErrorBottomSheet = true
                )
                Log.e("HomeViewModel", "❌ API error: $code - $message")
            },
            apiJobCallback = { job ->
                searchJob = job
            }
        )
    }
}