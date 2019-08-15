package com.mvvm_di_koin.module.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvm_di_koin.helper.SingleLiveEvent
import com.mvvm_di_koin.module.model.Article
import com.mvvm_di_koin.module.repository.NewsRepository
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class NewsViewModel(val query: String) : ViewModel(), KoinComponent {

    private val newsRepository by inject<NewsRepository> { parametersOf(viewModelScope) }

    val showLoading = MutableLiveData<Boolean>()
    val newsList = MutableLiveData<List<Article>>()
    val showError = SingleLiveEvent<String>()

    init {
        Log.d("Result", "Parameter = $query")
        getNews()
    }

    private fun getNews() {
        showLoading.value = true
        newsRepository.getEverything(query) { list, error ->
            showLoading.value = false
            list?.let {
                newsList.value = it
            }
            error?.let {
                showError.value = it
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}