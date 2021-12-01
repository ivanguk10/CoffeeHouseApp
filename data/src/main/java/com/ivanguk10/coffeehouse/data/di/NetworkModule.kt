package com.ivanguk10.coffeehouse.data.di

import com.ivanguk10.coffeehouse.data.Constants.Companion.BASE_URL
import com.ivanguk10.coffeehouse.data.api.CoffeeHouseApi
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
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()
}

//private val contentType = "application/json".toMediaType()
//private val jsonConfig = JsonConfiguration.Stable.copy(prettyPrint = true, ignoreUnknownKeys = true)
//private val json = Json(jsonConfig)
//

private fun provideConverterFactory(): MoshiConverterFactory {
    return MoshiConverterFactory.create()
}


private fun provideRetrofitInstance(
    okHttpClient: OkHttpClient,
    moshiConverterFactory: MoshiConverterFactory
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(moshiConverterFactory)
        .build()
}

private fun provideApiService(retrofit: Retrofit): CoffeeHouseApi {
    return retrofit.create(CoffeeHouseApi::class.java)
}
