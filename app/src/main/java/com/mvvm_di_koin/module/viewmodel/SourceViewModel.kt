package com.mvvm_di_koin.module.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvm_di_koin.helper.SingleLiveEvent
import com.mvvm_di_koin.module.model.Source
import com.mvvm_di_koin.module.repository.NewsRepository
import com.mvvm_di_koin.network.Result
import com.mvvm_di_koin.network.handlingResponse
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class SourceViewModel : ViewModel(), KoinComponent {

    private val newsRepository by inject<NewsRepository>()

    val showLoading = MutableLiveData<Boolean>()
    val sourceList = MutableLiveData<List<Source>>()
    val showError = SingleLiveEvent<String>()

    init {
        getSource()
    }

    private fun getSource() {
        showLoading.value = true
        viewModelScope.launch {
            try {
                val response = newsRepository.getSource()
                showLoading.value = false
                handlingResponse(response) {
                    when(it) {
                        is Result.Success -> sourceList.value = it.data.sources
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