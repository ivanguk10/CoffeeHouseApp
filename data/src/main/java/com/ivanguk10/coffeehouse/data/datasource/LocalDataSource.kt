package com.ivanguk10.coffeehouse.data.datasource

import androidx.lifecycle.LiveData
import com.ivanguk10.coffeehouse.data.database.CoffeeHouseDao
import com.ivanguk10.coffeehouse.data.database.HotProduct
import com.ivanguk10.coffeehouse.data.database.NewsAndSalesEntity

class LocalDataSource (
    private val coffeeHouseDao: com.ivanguk10.coffeehouse.data.database.CoffeeHouseDao
) {
    suspend fun insertNews(news: com.ivanguk10.coffeehouse.data.database.NewsAndSalesEntity) {
        coffeeHouseDao.insertNews(news)
    }

    fun readAllNews(): LiveData<List<com.ivanguk10.coffeehouse.data.database.NewsAndSalesEntity>> {
        return coffeeHouseDao.readSales()
    }

    suspend fun getAllNews(): List<com.ivanguk10.coffeehouse.data.database.NewsAndSalesEntity> {
        return coffeeHouseDao.getSales()
    }

    suspend fun insertHotProducts(product: com.ivanguk10.coffeehouse.data.database.HotProduct) {
        coffeeHouseDao.insertHotProducts(product)
    }

    fun readAllHotProducts(): LiveData<List<com.ivanguk10.coffeehouse.data.database.HotProduct>> {
        return coffeeHouseDao.readHotProducts()
    }
}