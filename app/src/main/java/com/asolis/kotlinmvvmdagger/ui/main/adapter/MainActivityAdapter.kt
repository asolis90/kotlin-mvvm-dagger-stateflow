package com.asolis.kotlinmvvmdagger.ui.main.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.asolis.kotlinmvvmdagger.data.models.Article
import com.asolis.kotlinmvvmdagger.databinding.ItemLayoutBinding
import com.asolis.kotlinmvvmdagger.databinding.MainActivityBinding
import com.bumptech.glide.Glide

class MainActivityAdapter(
    private val articleList: ArrayList<Article>
) : RecyclerView.Adapter<MainActivityAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.textViewTitle.text = article.title
            binding.textViewDescription.text = article.description
            binding.textViewSource.text = article.source.name
            Glide.with(binding.imageViewBanner.context)
                .load(article.imageUrl)
                .into(binding.imageViewBanner)
            itemView.setOnClickListener {
                Toast.makeText(it.context, "you clicked: ${article.title}", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = articleList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(articleList[position])

    fun addData(list: List<Article>) {
        articleList.addAll(list)
    }

}