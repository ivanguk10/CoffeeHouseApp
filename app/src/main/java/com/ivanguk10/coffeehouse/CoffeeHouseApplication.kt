package com.ivanguk10.coffeehouse

import android.app.Application
import com.ivanguk10.coffeehouse.data.di.databaseModule
import com.ivanguk10.coffeehouse.data.di.networkModule
import com.ivanguk10.coffeehouse.data.di.repositoryModule
import com.ivanguk10.coffeehouse.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.KoinExperimentalAPI
import org.koin.core.context.startKoin

class CoffeeHouseApplication: Application() {
    @KoinExperimentalAPI
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CoffeeHouseApplication)
            workManagerFactory()
            modules(listOf(networkModule, databaseModule, repositoryModule, viewModelModule))
        }
    }

}