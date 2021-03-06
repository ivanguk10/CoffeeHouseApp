package com.ivanguk10.coffeehouse.data.api

import com.ivanguk10.coffeehouse.data.database.entity.NewsAndSalesEntity
import com.ivanguk10.coffeehouse.data.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CoffeeHouseApi {
    @GET("news")
    suspend fun getNews(): Response<List<NewsAndSalesEntity>>

    @GET("news/{id}")
    suspend fun getNewsId(
        @Path("id") id: Int
    ): Response<NewsAndSalesEntity>

    @POST("news")
    suspend fun addNews(
        @Body news: NewsAndSalesEntity
    )
//////////
    @GET("coffee")
    suspend fun getCoffee(): Response<List<CoffeeModel>>

    @GET("coffee/{id}")
    suspend fun getCoffeeId(
        @Path("id") id: Int
    ): Response<CoffeeModel>

    @POST("coffee")
    suspend fun addCoffee(
        @Body coffee: CoffeeModel
    )
//////////
    @GET("tea")
    suspend fun getTea(): Response<List<TeaModel>>

    @GET("tea/{id}")
    suspend fun getTeaId(
        @Path("id") id: Int
    ): Response<TeaModel>

    @POST("tea")
    suspend fun addTea(
        @Body tea: TeaModel
    )
//////////
    @GET("drinks")
    suspend fun getDrink(): Response<List<DrinkModel>>

    @GET("drinks/{id}")
    suspend fun getDrinkId(
        @Path("id") id: Int
    ): Response<DrinkModel>

    @POST("drinks")
    suspend fun addDrink(
        @Body drink: DrinkModel
    )
//////////
    @GET("desserts")
    suspend fun getDesserts(): Response<List<DessertModel>>

    @GET("desserts/{id}")
    suspend fun getDessertId(
        @Path("id") id: Int
    ): Response<DessertModel>

    @POST("desserts")
    suspend fun addDessert(
        @Body dessert: DessertModel
    )
//////////
    @GET("altmilk")
    suspend fun getAlts(): Response<List<AltMilkModel>>

    @GET("altmilk/{id}")
    suspend fun getAltId(
        @Path("id") id: Int
    ): Response<AltMilkModel>

    @POST("altmilk")
    suspend fun addAlt(
        @Body alt: AltMilkModel
    )
}