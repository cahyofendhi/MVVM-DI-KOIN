package com.mvvm_di_koin.module.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.mvvm_di_koin.R
import com.mvvm_di_koin.module.adapter.NewsAdapter
import com.mvvm_di_koin.module.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_main.progress
import kotlinx.android.synthetic.main.home_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class HomeFragment : Fragment() {

    var id: String = "us"
    private  val  viewmodel: HomeViewModel by viewModel { parametersOf(id) }
    private lateinit var newsAdapter: NewsAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initViewModel()
    }

    private fun initAdapter() {
        newsAdapter = NewsAdapter()
        rvnews.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = newsAdapter
        }
    }

    private fun initViewModel() {
//        id = "Category"
//        viewModel = getViewModel { parametersOf(id) }


        viewmodel.newsList.observe(this, Observer {
            newsAdapter.updateData(it)
        })
        viewmodel.showLoading.observe(this, Observer {
            progress.visibility = if (it) View.VISIBLE else View.GONE
        })
        viewmodel.showError.observe(this, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

    }

}
