package com.asolis.kotlinmvvmdagger.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asolis.kotlinmvvmdagger.data.models.Article
import com.asolis.kotlinmvvmdagger.data.repository.DataRepositoryImpl
import com.asolis.kotlinmvvmdagger.ui.state.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MainActivityViewModel(private val dataRepositoryImpl: DataRepositoryImpl) : ViewModel() {

    private val _uiState = MutableStateFlow<UIState<List<Article>>>(UIState.Loading)

    val uiState: StateFlow<UIState<List<Article>>> = _uiState

    init {
        fetchTopHeadlines()
    }

    private fun fetchTopHeadlines() {
        viewModelScope.launch {
            dataRepositoryImpl.getTopNews("us")
                .catch { e ->
                    _uiState.value = UIState.Error(e.toString())
                }
                .collect {
                    _uiState.value = UIState.Success(it)
                }
        }
    }

}