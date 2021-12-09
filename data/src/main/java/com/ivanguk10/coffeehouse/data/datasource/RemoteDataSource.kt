package com.ivanguk10.coffeehouse.data.datasource

import android.util.Log
import com.ivanguk10.coffeehouse.data.api.CoffeeHouseApi
import com.ivanguk10.coffeehouse.data.model.CoffeeModel
import com.ivanguk10.coffeehouse.data.database.entity.NewsAndSalesEntity
import com.ivanguk10.coffeehouse.data.model.TeaModel
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

    suspend fun getCoffee(): Response<List<CoffeeModel>> {
        return coffeeHouseApi.getCoffee()
    }

    suspend fun getCoffeeId(id: Int): Response<CoffeeModel> {
        return coffeeHouseApi.getCoffeeId(id)
    }

    suspend fun addCoffee(coffee: CoffeeModel) {
        coffeeHouseApi.addCoffee(coffee)
    }

    suspend fun getTea(): Response<List<TeaModel>> {
        return coffeeHouseApi.getTea()
    }

    suspend fun getTeaId(id: Int): Response<TeaModel> {
        return coffeeHouseApi.getTeaId(id)
    }

    suspend fun addTea(tea: TeaModel) {
        coffeeHouseApi.addTea(tea)
    }
}