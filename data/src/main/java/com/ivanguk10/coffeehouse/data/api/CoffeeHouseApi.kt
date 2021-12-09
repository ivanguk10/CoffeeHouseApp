package com.ivanguk10.coffeehouse.data.api

import com.ivanguk10.coffeehouse.data.model.CoffeeModel
import com.ivanguk10.coffeehouse.data.database.entity.NewsAndSalesEntity
import com.ivanguk10.coffeehouse.data.model.TeaModel
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
    suspend fun getCoffee(): Response<List<CoffeeModel>>

    @GET("coffee/{id}")
    suspend fun getCoffeeId(
        @Path("id") id: Int
    ): Response<CoffeeModel>

    @POST("coffee")
    suspend fun addCoffee(
        @Body coffee: CoffeeModel
    )

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
}