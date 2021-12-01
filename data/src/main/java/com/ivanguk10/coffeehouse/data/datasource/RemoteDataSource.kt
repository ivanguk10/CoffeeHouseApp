package com.ivanguk10.coffeehouse.data.datasource

import android.util.Log
import com.ivanguk10.coffeehouse.data.api.CoffeeHouseApi
import com.ivanguk10.coffeehouse.data.database.entity.CoffeeEntity
import com.ivanguk10.coffeehouse.data.database.entity.NewsAndSalesEntity
import retrofit2.Response

class RemoteDataSource(private val coffeeHouseApi: CoffeeHouseApi) {

    suspend fun getNews(): Response<List<NewsAndSalesEntity>> {
        val news = coffeeHouseApi.getNews()
        Log.i("news", "remote ${news.toString()}")
        return news
    }

    suspend fun getNewsId(id: Int): Response<NewsAndSalesEntity> {
        return coffeeHouseApi.getNewsId(id)
    }

    suspend fun addNews(news: NewsAndSalesEntity) {
        coffeeHouseApi.addNews(news)
    }

    suspend fun getCoffee(): Response<List<CoffeeEntity>> {
        return coffeeHouseApi.getCoffee()
    }

    suspend fun getCoffeeId(id: Int): Response<CoffeeEntity> {
        return coffeeHouseApi.getCoffeeId(id)
    }

    suspend fun addCoffee(coffee: CoffeeEntity) {
        coffeeHouseApi.addCoffee(coffee)
    }
}