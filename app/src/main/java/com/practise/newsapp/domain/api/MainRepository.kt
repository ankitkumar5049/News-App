package com.practise.newsapp.domain.api

import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getNews(
        q: String? = null,
        fromDate: String? = null,
        apiKey: String? = null
    ): Response<String> {
        return apiService.getNews(q, fromDate, apiKey)
    }

}