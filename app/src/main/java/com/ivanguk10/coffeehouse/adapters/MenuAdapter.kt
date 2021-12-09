package com.ivanguk10.coffeehouse.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ivanguk10.coffeehouse.data.model.CoffeeModel
import com.ivanguk10.coffeehouse.data.model.TeaModel
import com.ivanguk10.coffeehouse.databinding.MenuItemLayoutBinding

class MenuAdapter(private var listId: Int): RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    private var coffeeList = listOf<CoffeeModel>()
    private var teaList = listOf<TeaModel>()

    class MenuViewHolder(val binding: MenuItemLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MenuItemLayoutBinding.inflate(layoutInflater, parent, false)

        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        when(listId) {
            1 -> {
                holder.binding.menuItemPriceValue.text = coffeeList[position].price.toString()
                holder.binding.menuItemName.text = coffeeList[position].name
            }
            2 -> {
                holder.binding.menuItemPriceValue.text = teaList[position].price.toString()
                holder.binding.menuItemName.text = teaList[position].name
            }
        }
    }

    override fun getItemCount(): Int {
        return when(listId) {
            1 ->  coffeeList.size
            2 ->  teaList.size
            else -> coffeeList.size
        }
    }

    fun setData(newMenuList: List<CoffeeModel>) {
        this.coffeeList = newMenuList
        notifyDataSetChanged()
    }
    fun setTeaData(newMenuList: List<TeaModel>) {
        this.teaList = newMenuList
        notifyDataSetChanged()
    }

}