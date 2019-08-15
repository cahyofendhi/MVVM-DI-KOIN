package com.mvvm_di_koin.module.repository

import com.mvvm_di_koin.module.model.Article
import com.mvvm_di_koin.module.model.Source
import com.mvvm_di_koin.network.Service
import com.mvvm_di_koin.network.Result
import com.mvvm_di_koin.network.request
import kotlinx.coroutines.CoroutineScope
import org.koin.core.KoinComponent
import org.koin.core.inject


class NewsRepository(private val scope: CoroutineScope) : KoinComponent {

    private val service by inject<Service>()

    /**
     * get top headline
     */
    fun getNewsList(id: String, response: (List<Article>?, String?) -> Unit) {
        request(scope, {service.getNews(id)}, { res ->
            when(res) {
                is Result.Success -> response(res.data.articles, null)
                is Result.Failure -> response(null, res.error)
            }
        })
    }

    /**
     * get news everything
     */
    fun getEverything(query: String, response: (List<Article>?, String?) -> Unit) {
        request(scope, {service.getEverything(query)}, { res ->
            when(res) {
                is Result.Success -> response(res.data.articles, null)
                is Result.Failure -> response(null, res.error)
            }
        })
    }

    /**
     * get source
     */
    fun getSource(response: (List<Source>?, String?) -> Unit) {
        request(scope, {service.getSources()}, { res ->
            when(res) {
                is Result.Success -> response(res.data.sources, null)
                is Result.Failure -> response(null, res.error)
            }
        })
    }


}