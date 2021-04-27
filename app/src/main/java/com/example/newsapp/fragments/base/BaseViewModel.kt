package com.example.newsapp.fragments.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.models.ApiFailModel
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


open class BaseViewModel : ViewModel() {
    val apiFailLiveData = MutableLiveData<ApiFailModel>()
    private var BASE_URL = "http://newsapi.org/"
    fun buildRetrofit(): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}
