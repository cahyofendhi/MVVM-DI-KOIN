package com.mvvm_di_koin.di.module

import com.mvvm_di_koin.module.repository.NewsRepository
import com.mvvm_di_koin.module.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val mainModule = module {

//    example when implementation interface
//    factory<NewsRepositoryImpl> {
//        NewsRepository()
//    }

    factory {
        (scope: CoroutineScope) -> NewsRepository(scope = scope)
    }

    viewModel {
//        MainViewModel() // without parameter
        (id: String) -> MainViewModel(id) // with dynamic paramater
    }

}
