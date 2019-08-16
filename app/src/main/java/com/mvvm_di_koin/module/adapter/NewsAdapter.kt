package com.mvvm_di_koin.module.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mvvm_di_koin.module.model.Article
import kotlin.properties.Delegates
import com.mvvm_di_koin.databinding.ItemNewsBinding
import com.mvvm_di_koin.module.viewmodel.ItemNewsViewModel


class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var newsList: List<Article> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemNewsBinding.inflate(layoutInflater, parent, false)

        return NewsViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            val cat: Article = newsList[position]
            holder.bind(cat)
        }
    }

    fun updateData(newsList: List<Article>) {
        this.newsList = newsList
    }

    class NewsViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(news: Article) {
            binding.vm = ItemNewsViewModel(news)
            Glide.with(binding.root.context)
                .load(news.urlToImage)
                .centerCrop()
                .thumbnail()
                .into(binding.imgNews)
        }
    }
}