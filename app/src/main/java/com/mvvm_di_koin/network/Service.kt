package com.mvvm_di_koin.network

import com.mvvm_di_koin.data.API_KEY
import com.mvvm_di_koin.module.model.News
import com.mvvm_di_koin.module.model.SourceResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("top-headlines")
    suspend fun getNews(@Query("country") country: String = "us",
                        @Query("apiKey") apiKey: String = API_KEY)
            : Response<News>

    @GET("everything")
    suspend fun getEverything(@Query("q") q: String = "",
                              @Query("apiKey") apiKey: String = API_KEY)
            : Response<News>

    @GET("sources")
    suspend fun getSources(@Query("apiKey") apiKey: String = API_KEY)
            : Response<SourceResponse>
}