package com.mvvm_di_koin.module.repository

import com.mvvm_di_koin.module.model.Article
import com.mvvm_di_koin.network.Service
import com.mvvm_di_koin.network.Result
import com.mvvm_di_koin.network.request
import org.koin.core.KoinComponent
import org.koin.core.inject


class NewsRepository : KoinComponent {

    private val service by inject<Service>()

    fun getNewsList(id: String, response: (List<Article>?, String?) -> Unit) {
        request({service.getNews(id)}, { res ->
            when(res) {
                is Result.Success -> response(res.data.articles, null)
                is Result.Failure -> response(null, res.error)
            }
        })
    }

}