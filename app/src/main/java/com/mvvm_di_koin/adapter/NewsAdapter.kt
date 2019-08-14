package com.mvvm_di_koin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mvvm_di_koin.R
import com.mvvm_di_koin.model.Article
import kotlinx.android.synthetic.main.item_news.view.*
import kotlin.properties.Delegates

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.CatViewHolder>() {

    private var catsList: List<Article> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_news, parent, false)
        return CatViewHolder(view)
    }

    override fun getItemCount(): Int = catsList.size

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            val cat: Article = catsList[position]
            holder.bind(cat)
        }
    }

    fun updateData(newCatsList: List<Article>) {
        catsList = newCatsList
    }

    class CatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(news: Article) {
            Glide.with(itemView.context)
                .load(news.urlToImage)
                .centerCrop()
                .thumbnail()
                .into(itemView.imgNews)
        }
    }
}