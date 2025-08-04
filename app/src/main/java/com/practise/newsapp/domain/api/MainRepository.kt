package com.practise.newsapp.domain.api

import com.practise.newsapp.domain.NewsResponse
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getNews(
        q: String? = null,
        pageSize: String? = null,
        fromDate: String? = null,
        apiKey: String? = null
    ): Response<NewsResponse> {
        return apiService.getNews(q, pageSize, fromDate, apiKey)
    }

}