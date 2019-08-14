package com.mvvm_di_koin.network

interface Mappable<out T : Any> {
    fun mapToResult(): Result<T>
}