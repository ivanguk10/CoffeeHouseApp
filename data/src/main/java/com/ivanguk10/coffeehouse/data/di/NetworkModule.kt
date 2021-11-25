package com.ivanguk10.coffeehouse.data.di

import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { provideHttpClient() }
    single { provideConverterFactory() }
    single { provideRetrofitInstance(get(), get()) }
    single { provideApiService(get()) }
}


private fun provideHttpClient() : OkHttpClient {
    return OkHttpClient.Builder()
        .readTimeout(15, TimeUnit.SECONDS)
        .connectTimeout(15, TimeUnit.SECONDS)
        .build()
}

private fun provideConverterFactory(): MoshiConverterFactory {
    return MoshiConverterFactory.create()
}

private fun provideRetrofitInstance(
    okHttpClient: OkHttpClient,
    moshiConverterFactory: MoshiConverterFactory
): Retrofit {
    return Retrofit.Builder()
        .baseUrl("BASE_URL")
        .client(okHttpClient)
        .addConverterFactory(moshiConverterFactory)
        .build()
}

private fun provideApiService(retrofit: Retrofit): com.ivanguk10.coffeehouse.data.api.CoffeeHouseApi {
    return retrofit.create(com.ivanguk10.coffeehouse.data.api.CoffeeHouseApi::class.java)
}
