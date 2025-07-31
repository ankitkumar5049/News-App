package com.practise.newsapp.domain.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/everything/")
    suspend fun getNews(
        @Query("q") query: String? = null,
        @Query("from") fromDate: String? = null, //2025-06-10
        @Query("apiKey") apiKey: String? = null

    ): Response<String>

}