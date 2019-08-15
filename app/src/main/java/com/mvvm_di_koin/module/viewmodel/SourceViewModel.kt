package com.mvvm_di_koin.module.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvm_di_koin.helper.SingleLiveEvent
import com.mvvm_di_koin.module.model.Source
import com.mvvm_di_koin.module.repository.NewsRepository
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class SourceViewModel() : ViewModel(), KoinComponent {

    private val newsRepository by inject<NewsRepository> { parametersOf(viewModelScope) }

    val showLoading = MutableLiveData<Boolean>()
    val newsList = MutableLiveData<List<Source>>()
    val showError = SingleLiveEvent<String>()

    init {
        getSource()
    }

    private fun getSource() {
        showLoading.value = true
        newsRepository.getSource { list, error ->
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