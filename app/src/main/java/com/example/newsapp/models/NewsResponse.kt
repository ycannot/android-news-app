package com.example.newsapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("status")
    @Expose
    var status: String? = null,
    @SerializedName("totalResults")
    @Expose
    var totalResults: Int? = null,
    @SerializedName("articles")
    @Expose
    var articles: ArrayList<Article>? = null
)
