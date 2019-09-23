package com.mvvm_di_koin.di.module

import com.mvvm_di_koin.module.repository.NewsRepository
import com.mvvm_di_koin.module.viewmodel.HomeViewModel
import com.mvvm_di_koin.module.viewmodel.NewsViewModel
import com.mvvm_di_koin.module.viewmodel.SourceViewModel
import kotlinx.coroutines.CoroutineScope
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val mainModule = module {

//    example when implementation interface
//    factory<NewsRepositoryImpl> {
//        NewsRepository()
//    }

    factory {
        NewsRepository()
    }

    viewModel {
//        MainViewModel() // without parameter
        (id: String) -> HomeViewModel(id) // with dynamic paramater
    }

    viewModel {
        (query: String) -> NewsViewModel(query = query)
    }

    viewModel {
        SourceViewModel()
    }

}
