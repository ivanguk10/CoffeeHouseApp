package com.ivanguk10.coffeehouse.data.datasource

import android.util.Log
import com.ivanguk10.coffeehouse.data.api.CoffeeHouseApi
import com.ivanguk10.coffeehouse.data.database.entity.NewsAndSalesEntity
import com.ivanguk10.coffeehouse.data.model.*
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

    suspend fun getDrink(): Response<List<DrinkModel>> {
        return coffeeHouseApi.getDrink()
    }

    suspend fun getDrinkId(id: Int): Response<DrinkModel> {
        return coffeeHouseApi.getDrinkId(id)
    }

    suspend fun addDrink(drink: DrinkModel) {
        coffeeHouseApi.addDrink(drink)
    }

    suspend fun getDesserts(): Response<List<DessertModel>> {
        return coffeeHouseApi.getDesserts()
    }

    suspend fun getDessertId(id: Int): Response<DessertModel> {
        return coffeeHouseApi.getDessertId(id)
    }

    suspend fun addDessert(dessert: DessertModel) {
        coffeeHouseApi.addDessert(dessert)
    }

    suspend fun getAlts(): Response<List<AltMilkModel>> {
        return coffeeHouseApi.getAlts()
    }

    suspend fun getAltId(id: Int): Response<AltMilkModel> {
        return coffeeHouseApi.getAltId(id)
    }

    suspend fun addAlt(dessert: AltMilkModel) {
        coffeeHouseApi.addAlt(dessert)
    }
}