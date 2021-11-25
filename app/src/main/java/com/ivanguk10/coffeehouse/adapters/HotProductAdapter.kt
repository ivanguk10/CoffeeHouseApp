package com.ivanguk10.coffeehouse.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ivanguk10.coffeehouse.databinding.HotProductsBinding

class HotProductAdapter: RecyclerView.Adapter<HotProductAdapter.HotProductViewHolder>() {

    private var hotProducts = listOf<com.ivanguk10.coffeehouse.data.database.HotProduct>()
    //
    class HotProductViewHolder(val binding: HotProductsBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HotProductsBinding.inflate(layoutInflater, parent, false)

        return HotProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HotProductViewHolder, position: Int) {
        val hotProducts = hotProducts[position]

        holder.binding.hotProductTitle.text = hotProducts.name
        holder.binding.hotProductPrice.text = hotProducts.price.toString()
    }

    override fun getItemCount(): Int {
        return hotProducts.size
    }

    fun setData(newListOfNewsAndSales: List<com.ivanguk10.coffeehouse.data.database.HotProduct>) {
        this.hotProducts = newListOfNewsAndSales
        notifyDataSetChanged()
    }
}