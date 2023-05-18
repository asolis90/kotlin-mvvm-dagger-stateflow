package com.asolis.kotlinmvvmdagger.data.repository

import com.asolis.kotlinmvvmdagger.data.models.Article
import kotlinx.coroutines.flow.Flow

interface DataRepository {
    fun getTopNews(country: String): Flow<List<Article>>
}