package com.ivanguk10.coffeehouse.data.datasource

import androidx.lifecycle.LiveData
import com.ivanguk10.coffeehouse.data.database.CoffeeHouseDao
import com.ivanguk10.coffeehouse.data.database.entity.HotProduct
import com.ivanguk10.coffeehouse.data.database.entity.NewsAndSalesEntity

class LocalDataSource (
    private val coffeeHouseDao: CoffeeHouseDao
) {
    suspend fun insertNews(news: NewsAndSalesEntity) {
        coffeeHouseDao.insertNews(news)
    }

    fun readAllNews(): LiveData<List<NewsAndSalesEntity>> {
        return coffeeHouseDao.readSales()
    }

    suspend fun getAllNews(): List<NewsAndSalesEntity> {
        return coffeeHouseDao.getSales()
    }

    suspend fun insertHotProducts(product: HotProduct) {
        coffeeHouseDao.insertHotProducts(product)
    }

    fun readAllHotProducts(): LiveData<List<HotProduct>> {
        return coffeeHouseDao.readHotProducts()
    }
}