package com.asolis.kotlinmvvmdagger.data.repository

import com.asolis.kotlinmvvmdagger.data.models.Article
import com.asolis.kotlinmvvmdagger.data.networking.NewsAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class DataRepositoryImpl(
    private val newsAPI: NewsAPI
) : DataRepository {

    override fun getTopNews(country: String): Flow<List<Article>> {
        return flow {
            emit(newsAPI.getTopHeadlines(country))
        }.map {
            it.articles
        }
    }
}