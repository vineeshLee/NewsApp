package com.vineesh.newsapp

import Articles
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.vineesh.newsapp.databinding.AdapterNewsItemBinding
import java.text.SimpleDateFormat
import java.util.*

class NewsListAdapter(private val listener: NewsItemListener) : RecyclerView.Adapter<NewsViewHolder>() {

    interface NewsItemListener {
        fun onClickedNews(item: Articles)
    }

    private val newsList = mutableListOf<Articles>()

    fun setItems(items: MutableList<Articles>) {
        this.newsList.clear()
            this.newsList.addAll(items)
            notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding: AdapterNewsItemBinding = AdapterNewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.bind(newsList[position])
    }
}

class NewsViewHolder(
    private val itemBinding: AdapterNewsItemBinding,
    private val listener: NewsListAdapter.NewsItemListener

) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var itemNews: Articles

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: Articles) {
        this.itemNews = item

        itemNews.title?.let {
            itemBinding.title.text = it
        }

            itemNews.urlToImage.let {
               try {
                   Glide.with(itemBinding.root.context)
                       .load(it)
                       .transition(withCrossFade())
                       .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                       .thumbnail(0.5f)
                       .into(itemBinding.newsImage)

               }catch (e:Exception)
               {

               }

            }




        val input = itemNews.publishedAt
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SS'Z'")
        val outputFormat = SimpleDateFormat("MMM dd yyyy")
        val parsedDate = inputFormat.parse(input)
        val formattedDate = outputFormat.format(parsedDate)
        itemBinding.date.text=formattedDate+" by "


        itemNews.description?.let {
            itemBinding.textViewDescription.text = it
        }
        itemNews.author.let {
            if (it.isNullOrEmpty())
            {
                itemBinding.author.text=itemBinding.root.context.resources.getString(R.string.not_available)
            }else
            {
                itemBinding.author.text=it.toString()
            }

        }


       /* itemNews.source?.let {
            it.name.let {
                itemBinding.type.text = it
            }

        }*/




        itemBinding.materialCardView.setOnClickListener {
            listener.onClickedNews(itemNews)
        }

    }


    override fun onClick(v: View?) {
        listener.onClickedNews(itemNews)
    }
}

