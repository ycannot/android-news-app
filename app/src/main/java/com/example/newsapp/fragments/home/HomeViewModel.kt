package com.example.newsapp.fragments.home

import android.telecom.Call
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.fragments.base.BaseViewModel
import com.example.newsapp.interfaces.NewsApiInterface
import com.example.newsapp.models.ApiFailModel
import com.example.newsapp.models.NewsResponse
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : BaseViewModel() {
    val newsLiveData:MutableLiveData<NewsResponse> = MutableLiveData()
    fun getFootballNews(startDateString: String){
        val apiService = buildRetrofit().create(NewsApiInterface::class.java)
        val call = apiService.getNews("ae68088e70d04639b4950bdc9d546924", "football", "publishedAt", startDateString)
        call.request()
        println(call.request())
        call.enqueue(object: Callback<NewsResponse> {
            override fun onResponse(
                call: retrofit2.Call<NewsResponse>,
                response: Response<NewsResponse>
            ) {
                if (response.isSuccessful){
                    newsLiveData.postValue(response.body())
                } else{
                    apiFailLiveData.postValue(ApiFailModel(response.code(), response.message()))
                }
            }

            override fun onFailure(call: retrofit2.Call<NewsResponse>, t: Throwable) {
                apiFailLiveData.postValue(ApiFailModel(message = t.message))
                t.printStackTrace()
            }
        })
    }

}