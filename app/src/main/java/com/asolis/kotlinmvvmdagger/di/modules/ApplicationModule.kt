package com.asolis.kotlinmvvmdagger.di.modules

import android.content.Context
import com.asolis.kotlinmvvmdagger.KotlinMVVMDaggerApplication
import com.asolis.kotlinmvvmdagger.di.ApplicationContext
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val application: KotlinMVVMDaggerApplication) {

    @ApplicationContext
    @Provides
    fun provideContext(): Context {
        return application
    }
}