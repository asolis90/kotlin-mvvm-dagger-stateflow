package com.asolis.kotlinmvvmdagger.di

import javax.inject.Scope
import javax.inject.Qualifier

@Scope
@Retention(AnnotationRetention.SOURCE)
annotation class ActivityScope

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class ApplicationContext

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class ActivityContext