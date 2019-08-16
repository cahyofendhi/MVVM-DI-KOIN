package com.mvvm_di_koin.module.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mvvm_di_koin.R
import com.mvvm_di_koin.module.model.Source
import kotlinx.android.synthetic.main.item_source.view.*
import kotlin.properties.Delegates

class SourceAdapter : RecyclerView.Adapter<SourceAdapter.SourceViewHolder>() {

    private var sourceList: List<Source> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_source, parent, false)
        return SourceViewHolder(view)
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

    class SourceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(source: Source) {
            itemView.textTitle.text = source.name
            itemView.textDescription.text = source.description
        }
    }
}