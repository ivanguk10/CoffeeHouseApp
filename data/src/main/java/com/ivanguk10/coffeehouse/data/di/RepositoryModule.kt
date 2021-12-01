package com.ivanguk10.coffeehouse.data.di


import com.ivanguk10.coffeehouse.data.datasource.LocalDataSource
import com.ivanguk10.coffeehouse.data.datasource.RemoteDataSource
import com.ivanguk10.coffeehouse.data.repository.Repository
import org.koin.dsl.module

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single { Repository(get(), get()) }
}