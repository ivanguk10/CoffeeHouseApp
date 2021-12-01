package com.ivanguk10.coffeehouse.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ivanguk10.coffeehouse.R
import com.ivanguk10.coffeehouse.data.database.entity.NewsAndSalesEntity
import com.ivanguk10.coffeehouse.databinding.NewsAndSalesItemBinding

class NewsAndSalesAdapter(
    private val context: Context
): RecyclerView.Adapter<NewsAndSalesAdapter.NewsViewHolder>() {

    private var listOfNewsAndSales = listOf<NewsAndSalesEntity>()
//
    class NewsViewHolder(val binding: NewsAndSalesItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = NewsAndSalesItemBinding.inflate(layoutInflater, parent, false)

        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsAndSales = listOfNewsAndSales[position]

        holder.binding.newsTitleTv.text = newsAndSales.title
        holder.binding.newsDescription.text = newsAndSales.description
        when(position % 5) {
            0 -> {
                holder.binding.newsCardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.darkPink))
            }
            1 -> {
                holder.binding.newsCardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.yellow))
            }
            2 -> {
                holder.binding.newsCardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.pink))
            }
            3 -> {
                holder.binding.newsCardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.green))
            }
            4 -> {
                holder.binding.newsCardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.orange))
            }
        }
    }

    override fun getItemCount(): Int {
        return listOfNewsAndSales.size
    }

    fun setData(newListOfNewsAndSales: List<NewsAndSalesEntity>) {
        this.listOfNewsAndSales = newListOfNewsAndSales
        notifyDataSetChanged()
    }
}