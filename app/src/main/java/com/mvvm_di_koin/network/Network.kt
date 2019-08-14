package com.mvvm_di_koin.network

import android.util.Log
import kotlinx.coroutines.*
import retrofit2.Response


fun <T> request(response: suspend() -> Response<T>,
                results: (Result<T>) -> Unit) {

    GlobalScope.launch(Dispatchers.Main) {
        handlingResponse(response, {results(it)})
    }
}

fun <T> request(coroutineScope: CoroutineScope,
                response: suspend() -> Response<T>,
                results:(Result<T>) -> Unit) {
    coroutineScope.launch {
        handlingResponse(response, {results(it)})
    }
}

fun <T> requestBlock(response: suspend() -> Response<T>,
                     results: (Result<T>) -> Unit) {
    runBlocking {
        handlingResponse(response, {results(it)})
    }
}

suspend fun <T> handlingResponse(response: suspend() -> Response<T>,
                                 results:(Result<T>) -> Unit) {
    try {
        val result = response()
        if (result.isSuccessful) {
            result.body()?.let { body ->
                results(Result.Success(body))
            }
        } else {
            Log.d("Result", result.toString())
            results(Result.Failure(result.toString()))
        }
    } catch (throwable: Throwable) {
        results(Result.Failure(throwable.toString()))
    }
}