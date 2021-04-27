package com.example.newsapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ArticleSource(
    @SerializedName("id")
    @Expose
    var id: String? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null
)
