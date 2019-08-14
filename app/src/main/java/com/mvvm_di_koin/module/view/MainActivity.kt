package com.mvvm_di_koin.module.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.mvvm_di_koin.R
import com.mvvm_di_koin.module.adapter.NewsAdapter
import com.mvvm_di_koin.module.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity() {

    var id: String = "us"
    private  val  viewModel: MainViewModel by viewModel { parametersOf(id) }
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        initAdapter()
    }

    private fun initAdapter() {
        newsAdapter = NewsAdapter()
        rv.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            adapter = newsAdapter
        }
    }

    private fun initViewModel() {
//        id = "Category"
//        viewModel = getViewModel { parametersOf(id) }


        viewModel.newsList.observe(this, Observer {
            newsAdapter.updateData(it)
        })
        viewModel.showLoading.observe(this, Observer {
            progress.visibility = if (it) View.VISIBLE else View.GONE
        })
        viewModel.showError.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

    }

}
