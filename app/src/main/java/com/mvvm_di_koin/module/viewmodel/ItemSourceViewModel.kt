package com.mvvm_di_koin.module.viewmodel

import androidx.lifecycle.MutableLiveData
import com.mvvm_di_koin.module.model.Source

class ItemSourceViewModel (val source: Source) {

    val data = MutableLiveData<Source>()

    init {
        data.value = source
    }

}