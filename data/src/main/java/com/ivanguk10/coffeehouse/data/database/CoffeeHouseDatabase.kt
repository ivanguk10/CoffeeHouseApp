package com.ivanguk10.coffeehouse.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [NewsAndSalesEntity::class, HotProduct::class],
    version = 1,
    exportSchema = false
)


abstract class CoffeeHouseDatabase: RoomDatabase() {
    abstract fun coffeeHouseDao(): CoffeeHouseDao
}