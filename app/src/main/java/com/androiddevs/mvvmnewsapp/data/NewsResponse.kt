package com.androiddevs.mvvmnewsapp.data

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)