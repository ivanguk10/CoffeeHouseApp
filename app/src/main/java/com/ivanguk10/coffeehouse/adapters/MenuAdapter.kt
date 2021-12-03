package com.ivanguk10.coffeehouse.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ivanguk10.coffeehouse.data.database.entity.CoffeeEntity
import com.ivanguk10.coffeehouse.databinding.MenuItemLayoutBinding

class MenuAdapter: RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    private var menuList = listOf<CoffeeEntity>()

    class MenuViewHolder(val binding: MenuItemLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MenuItemLayoutBinding.inflate(layoutInflater, parent, false)

        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val menuItem = menuList[position]

        holder.binding.menuItemPriceValue.text = menuItem.price.toString()
        holder.binding.menuItemName.text = menuItem.name
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    fun setData(newMenuList: List<CoffeeEntity>) {
        this.menuList = newMenuList
        notifyDataSetChanged()
    }

}