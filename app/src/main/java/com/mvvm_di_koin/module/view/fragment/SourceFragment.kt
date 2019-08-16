package com.mvvm_di_koin.module.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mvvm_di_koin.R
import com.mvvm_di_koin.module.adapter.SourceAdapter
import com.mvvm_di_koin.module.viewmodel.SourceViewModel
import kotlinx.android.synthetic.main.fragment_source.*
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.home_fragment.progress
import org.koin.androidx.viewmodel.ext.android.viewModel


class SourceFragment : Fragment() {


    private  val  viewmodel: SourceViewModel by viewModel()
    private lateinit var sourceAdapter: SourceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_source, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initViewModel()
    }

    private fun initAdapter() {
        sourceAdapter = SourceAdapter()
        rvsource.apply {
            val linearVertical = LinearLayoutManager(context, RecyclerView.VERTICAL,false)
            layoutManager = linearVertical
            adapter = sourceAdapter
        }
    }

    private fun initViewModel() {
        viewmodel.sourceList.observe(this, Observer {
            sourceAdapter.updateData(it)
        })
        viewmodel.showLoading.observe(this, Observer {
            progress.visibility = if (it) View.VISIBLE else View.GONE
        })
        viewmodel.showError.observe(this, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })

    }

}
