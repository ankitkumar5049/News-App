package com.practise.newsapp.presentation.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.practise.newsapp.common.viewmodel.BaseViewModel
import com.practise.newsapp.domain.Articles
import com.practise.newsapp.domain.api.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MainRepository
) : BaseViewModel() {
    private val newsCache = mutableMapOf<String, List<Articles>>()

    var state by mutableStateOf(HomeContract.state())
    private var searchJob: Job? = null


    val formattedDate = getYesterdayDate()

    fun getNews(
        query: String = "Trending",
        pageSize: Int = 20,
        fromDate: String = formattedDate,
        apiKey: String = "81fa24457e154a7a844c37bdb5e1c168"
    ) {

        newsCache[query]?.let { cachedArticles ->
            state = state.copy(articles = cachedArticles)
            return
        }
        apiCallWithJob(
            api = {
                repository.getNews(
                    q = query,
                    pageSize = pageSize.toString(),
                    fromDate = fromDate,
                    apiKey = apiKey
                )
            },
            onSuccess = { result ->
                state = state.copy(articles = result.articles)
            },
            onError = { code, message, body ->
                apiState = apiState.copy(
                    apiErrorMessage = "Error $code",
                    apiErrorDescription = body ?: message,
                    isApiSuccessful = false,
                    showErrorBottomSheet = true
                )
            },
            apiJobCallback = { job ->
                searchJob = job
            }
        )
    }


    fun getYesterdayDate(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, -1)

        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return formatter.format(calendar.time)
    }
}