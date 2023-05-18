package com.asolis.kotlinmvvmdagger.data.models

import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("status")
    val status: String = "",
    @SerializedName("totalResults")
    val totalResults: Int = 0,
    @SerializedName("articles")
    val articles: List<Article> = ArrayList(),
)