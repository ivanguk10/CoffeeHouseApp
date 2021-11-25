package com.ivanguk10.coffeehouse.data.repository

import com.ivanguk10.coffeehouse.data.datasource.LocalDataSource

class Repository (
    localDataSource: LocalDataSource,
//    private val remoteDataSource: RemoteDataSource
) {
    val local = localDataSource
//    val remote = remoteDataSource
}