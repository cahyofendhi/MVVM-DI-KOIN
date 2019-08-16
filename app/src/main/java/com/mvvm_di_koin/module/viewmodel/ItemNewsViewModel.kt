package com.mvvm_di_koin.module.viewmodel

import androidx.lifecycle.MutableLiveData
import com.mvvm_di_koin.module.model.Article

class ItemNewsViewModel (var data: Article) {

    var news = MutableLiveData<Article>()

    init {
        news.value = data
    }

}

