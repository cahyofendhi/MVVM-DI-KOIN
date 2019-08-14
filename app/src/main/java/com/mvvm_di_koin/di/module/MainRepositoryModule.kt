package com.mvvm_di_koin.di.module

import com.mvvm_di_koin.repository.NewsRepository
import com.mvvm_di_koin.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val mainRepositoryModule = module {

//    example when implementation interface
//    factory<CatRepositoryImpl> {
//        CatRepository()
//    }

    factory {
        NewsRepository()
    }

    viewModel {
//        MainViewModel() // without parameter
        (id: String) -> MainViewModel(id) // with dynamic paramater
    }

}
