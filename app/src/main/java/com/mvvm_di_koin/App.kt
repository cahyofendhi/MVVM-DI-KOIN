package com.mvvm_di_koin

import android.app.Application
import com.mvvm_di_koin.di.module.appModules
import com.mvvm_di_koin.di.module.mainRepositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(listOf(appModules, mainRepositoryModule))
        }
    }

}