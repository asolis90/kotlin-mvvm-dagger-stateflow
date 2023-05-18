package com.asolis.kotlinmvvmdagger

import android.app.Application
import com.asolis.kotlinmvvmdagger.di.components.ApplicationComponent
import com.asolis.kotlinmvvmdagger.di.components.DaggerApplicationComponent
import com.asolis.kotlinmvvmdagger.di.modules.ApplicationModule
import com.asolis.kotlinmvvmdagger.di.modules.UIModule

open class KotlinMVVMDaggerApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .uIModule(UIModule(this))
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}