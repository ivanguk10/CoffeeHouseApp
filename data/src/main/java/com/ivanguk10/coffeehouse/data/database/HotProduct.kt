package com.ivanguk10.coffeehouse.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hot_products")
data class HotProduct(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val price: Int
) {
}