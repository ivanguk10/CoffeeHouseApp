package com.ivanguk10.coffeehouse.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "news")
@JsonClass(generateAdapter = true)
data class NewsAndSalesEntity(
    @PrimaryKey(autoGenerate = false)
    @Json(name = "id")
    var id: Int,
    @Json(name = "title")
    var title: String,
    @Json(name = "description")
    var description: String
)
