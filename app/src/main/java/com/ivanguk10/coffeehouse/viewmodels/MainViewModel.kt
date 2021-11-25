package com.ivanguk10.coffeehouse.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class MainViewModel (
    private val repository: com.ivanguk10.coffeehouse.data.repository.Repository
): ViewModel() {

    fun insertNews(newsAndSalesEntity: com.ivanguk10.coffeehouse.data.database.NewsAndSalesEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.insertNews(newsAndSalesEntity)
        }
    }

    fun insertHotProduct(hotProduct: com.ivanguk10.coffeehouse.data.database.HotProduct) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.insertHotProducts(hotProduct)
        }
    }

    val readNewsAndSales: LiveData<List<com.ivanguk10.coffeehouse.data.database.NewsAndSalesEntity>> = repository.local.readAllNews()
    val readHotProducts: LiveData<List<com.ivanguk10.coffeehouse.data.database.HotProduct>> = repository.local.readAllHotProducts()


}