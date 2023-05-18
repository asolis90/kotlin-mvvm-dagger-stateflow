package com.asolis.kotlinmvvmdagger.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.asolis.kotlinmvvmdagger.data.repository.DataRepositoryImpl

@Suppress("UNCHECKED_CAST")
class MainActivityViewModelFactory(
    private val repository: DataRepositoryImpl
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalArgumentException::class)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainActivityViewModel(repository) as T
    }
}