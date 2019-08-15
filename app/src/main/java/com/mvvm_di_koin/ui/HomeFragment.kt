package com.mvvm_di_koin.ui

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
import com.mvvm_di_koin.module.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class HomeFragment : Fragment() {

    var id: String = "us"
    private  val  viewModel: MainViewModel by viewModel { parametersOf(id) }
    private lateinit var newsAdapter: NewsAdapter

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initAdapter()
        initViewModel()
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    private fun initAdapter() {
        newsAdapter = NewsAdapter()
        rv.apply {
            layoutManager = GridLayoutManager(context, 3)
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
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

    }

}
