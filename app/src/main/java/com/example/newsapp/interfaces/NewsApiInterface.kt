package com.example.newsapp.interfaces

import com.example.newsapp.models.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsApiInterface {
    @GET("v2/everything")
    fun getNews(
        @Query("apiKey") apiKey: String,
        @Query("q") query: String,
        @Query("sortBy") sortBy: String,
        @Query("from") from: String,
        @Query("pageSize") pageSize: Int
    ): Call<NewsResponse>
}