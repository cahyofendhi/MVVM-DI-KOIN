package com.mvvm_di_koin.module.repository

import android.util.Log
import com.mvvm_di_koin.module.model.Article
import com.mvvm_di_koin.module.model.News
import com.mvvm_di_koin.module.model.Source
import com.mvvm_di_koin.module.model.SourceResponse
import com.mvvm_di_koin.network.Service
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Response


class NewsRepository : KoinComponent {

    private val service by inject<Service>()

    /**
     * get top headline
     */
    suspend fun getNewsList(id: String): Response<News> {
        return coroutineScope {
            val defer = async { service.getNews(id) }
            val response = defer.await()
            response
        }
    }

    /**
     * get news everything
     */
    suspend fun getEverything(query: String): Response<News> {
        return coroutineScope {
            val defer = async { service.getEverything(query) }
            val response = defer.await()
            response
        }
    }

    /**
     * get source
     */
    suspend fun getSource(): Response<SourceResponse> {
        return coroutineScope {
            val defer = async { service.getSources() }
            val response = defer.await()
            response
        }
    }

}
