package com.mvvm_di_koin.module.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvm_di_koin.helper.SingleLiveEvent
import com.mvvm_di_koin.module.model.Article
import com.mvvm_di_koin.module.repository.NewsRepository
import com.mvvm_di_koin.network.Result
import com.mvvm_di_koin.network.handlingResponse
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class NewsViewModel(val query: String) : ViewModel(), KoinComponent {

    private val newsRepository by inject<NewsRepository>()

    val showLoading = MutableLiveData<Boolean>()
    val newsList = MutableLiveData<List<Article>>()
    val showError = SingleLiveEvent<String>()

    init {
        Log.d("Result", "Parameter = $query")
        getNews()
    }

    private fun getNews() {
        showLoading.value = true
        viewModelScope.launch {
            try {
                val response = newsRepository.getEverything(query)
                showLoading.value = false
                handlingResponse(response) {
                    when(it) {
                        is Result.Success -> newsList.value = it.data.articles
                        is Result.Failure -> showError.value = it.error
                    }
                }
            } catch (e: Exception) {
                showError.value = e.toString()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}