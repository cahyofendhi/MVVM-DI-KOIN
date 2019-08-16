package com.mvvm_di_koin.module.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mvvm_di_koin.databinding.ItemSourceBinding
import com.mvvm_di_koin.module.model.Source
import com.mvvm_di_koin.module.viewmodel.ItemSourceViewModel
import kotlin.properties.Delegates

class SourceAdapter : RecyclerView.Adapter<SourceAdapter.SourceViewHolder>() {

    private var sourceList: List<Source> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemSourceBinding.inflate(layoutInflater, parent, false)

        return SourceViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = sourceList.size

    override fun onBindViewHolder(holder: SourceViewHolder, position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            val data = sourceList[position]
            holder.bind(data)
        }
    }

    fun updateData(sourceList: List<Source>) {
        this.sourceList = sourceList
    }

    class SourceViewHolder(val binding: ItemSourceBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(source: Source) {
            binding.vm = ItemSourceViewModel(source)
        }
    }
}