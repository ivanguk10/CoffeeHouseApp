package com.ivanguk10.coffeehouse.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsAndSalesEntity(
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    val title: String,
    val description: String
)
