package com.asolis.kotlinmvvmdagger.di.components

import android.content.Context
import com.asolis.kotlinmvvmdagger.KotlinMVVMDaggerApplication
import com.asolis.kotlinmvvmdagger.di.ApplicationContext
import com.asolis.kotlinmvvmdagger.di.modules.ApplicationModule
import com.asolis.kotlinmvvmdagger.di.modules.UIModule
import com.asolis.kotlinmvvmdagger.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, UIModule::class])
interface ApplicationComponent {
    @ApplicationContext
    fun getContext(): Context

    fun inject(application: KotlinMVVMDaggerApplication)
    
    fun inject(activity: MainActivity)
}