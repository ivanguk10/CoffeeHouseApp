package com.ivanguk10.coffeehouse.data.di

import android.app.Application
import androidx.room.Room
import com.ivanguk10.coffeehouse.data.Constants.Companion.DATABASE_NAME
import com.ivanguk10.coffeehouse.data.database.CoffeeHouseDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val databaseModule = module {
    single { provideDatabase(androidApplication()) }
    single { provideData(get()) }
}

    private fun provideDatabase(
        application: Application
    ) = Room.databaseBuilder(
        application,
        CoffeeHouseDatabase::class.java,
        DATABASE_NAME
    ).build()

    private fun provideData(coffeeHouseDatabase: CoffeeHouseDatabase) =
        coffeeHouseDatabase.coffeeHouseDao()

