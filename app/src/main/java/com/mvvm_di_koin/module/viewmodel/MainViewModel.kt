package com.mvvm_di_koin.module.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvvm_di_koin.helper.SingleLiveEvent
import com.mvvm_di_koin.module.model.Article
import com.mvvm_di_koin.module.repository.NewsRepository
import kotlinx.coroutines.*
import org.koin.core.KoinComponent
import org.koin.core.inject
import kotlin.coroutines.CoroutineContext


class MainViewModel(val id: String) : ViewModel(), CoroutineScope, KoinComponent {

    private val newsRepository by inject<NewsRepository>()

    private val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    val showLoading = MutableLiveData<Boolean>()
    val newsList = MutableLiveData<List<Article>>()
    val showError = SingleLiveEvent<String>()

    init {
        Log.d("Result", "Parameter = $id")
        getNews()
    }

    private fun getNews() {
        showLoading.value = true
        newsRepository.getNewsList(id) { list, throwable ->
            showLoading.value = false
            list?.let {
                newsList.value = it
            }
            throwable?.let {
                showError.value = it
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}