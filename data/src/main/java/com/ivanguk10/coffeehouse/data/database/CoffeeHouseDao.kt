package com.ivanguk10.coffeehouse.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ivanguk10.coffeehouse.data.database.entity.HotProduct
import com.ivanguk10.coffeehouse.data.database.entity.NewsAndSalesEntity

@Dao
interface CoffeeHouseDao {
    @Insert
    suspend fun insertNews(news: NewsAndSalesEntity)

    @Query("SELECT * FROM news ORDER by id ASC")
    fun readSales(): LiveData<List<NewsAndSalesEntity>>

    @Query("SELECT * FROM news ORDER by id ASC")
    suspend fun getSales(): List<NewsAndSalesEntity>

    @Insert
    suspend fun insertHotProducts(product: HotProduct)

    @Query("SELECT * FROM hot_products ORDER by id ASC")
    fun readHotProducts(): LiveData<List<HotProduct>>
}