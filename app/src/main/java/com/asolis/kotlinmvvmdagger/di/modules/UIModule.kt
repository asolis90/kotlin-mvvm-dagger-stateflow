package com.asolis.kotlinmvvmdagger.di.modules

import android.content.Context
import com.asolis.kotlinmvvmdagger.data.networking.ApiBuilder
import com.asolis.kotlinmvvmdagger.data.networking.ApiConfiguration
import com.asolis.kotlinmvvmdagger.data.networking.NewsAPI
import dagger.Module
import dagger.Provides
import com.asolis.kotlinmvvmdagger.data.repository.DataRepositoryImpl
import com.asolis.kotlinmvvmdagger.di.ActivityContext
import com.asolis.kotlinmvvmdagger.ui.main.MainActivityViewModelFactory
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.Request
import java.io.File

@Module
class UIModule(private val context: Context) {

    @ActivityContext
    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun providesMainActivityViewModelFactory(dataRepositoryImpl: DataRepositoryImpl): MainActivityViewModelFactory {
        return MainActivityViewModelFactory(repository = dataRepositoryImpl)
    }

    @Provides
    fun providesRepository(): DataRepositoryImpl {
        // cache size of 10MB
        val cacheSize = 10 * 1024 * 1024
        val cache = Cache(File(context.cacheDir, NewsAPI.HTTP_CACHE_FILENAME), cacheSize.toLong())
        val apiBaseUrl: String = NewsAPI.BASE_URL

        return DataRepositoryImpl(
            ApiBuilder(context, ApiConfiguration(cache, Interceptor {
                val newRequest = Request.Builder()
                    .addHeader("X-Api-Key", NewsAPI.API_KEY)
                    .url(it.request().url())
                    .build()

                it.proceed(newRequest)

            })).buildApi(apiBaseUrl, NewsAPI::class.java)
        )
    }
}