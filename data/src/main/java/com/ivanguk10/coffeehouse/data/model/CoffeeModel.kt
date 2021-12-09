package com.ivanguk10.coffeehouse.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoffeeModel(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    var name: String,
    @Json(name = "price")
    var price: Float
)
