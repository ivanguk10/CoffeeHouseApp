package com.ivanguk10.coffeehouse.data.api

import com.ivanguk10.coffeehouse.data.database.entity.CoffeeEntity
import com.ivanguk10.coffeehouse.data.database.entity.NewsAndSalesEntity
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

    @GET("coffee")
    suspend fun getCoffee(): Response<List<CoffeeEntity>>

    @GET("coffee/{id}")
    suspend fun getCoffeeId(
        @Path("id") id: Int
    ): Response<CoffeeEntity>

    @POST("coffee")
    suspend fun addCoffee(
        @Body coffee: CoffeeEntity
    )
}