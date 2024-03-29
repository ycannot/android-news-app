package com.example.newsapp.fragments.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.ListItemNewsBinding
import com.example.newsapp.models.Article
import com.squareup.picasso.Picasso
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class NewsListAdapter(
    var articles: ArrayList<Article> = arrayListOf(),
    var listener: ((url: String) -> Unit)? = null
) : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {
    private lateinit var binding: ListItemNewsBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ListItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    inner class ViewHolder(private val itemBinding: ListItemNewsBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(article: Article) {
            try {
                Picasso.get().load(article.urlToImage).into(itemBinding.ivNew)
            } catch (ignored: Exception) {}
            itemBinding.tvTitle.text = article.title
            itemBinding.tvDescription.text = article.description
            itemBinding.tvSource.text = article.source?.name
            itemBinding.tvDate.text = formatDateString(article.publishedAt)
            itemBinding.root.setOnClickListener {
                article.url?.let {
                    listener?.invoke(it)
                }
            }
        }
        private fun formatDateString(dateString: String?):String{
            if (dateString == null){
                return "Date"
            }
            val fmt = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            return try {
                val date: Date = fmt.parse(dateString) ?: Date()
                fmt.format(date)
            } catch (pe: ParseException) {
                "Date"
            }
        }
    }

}