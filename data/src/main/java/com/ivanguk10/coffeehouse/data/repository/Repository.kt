package com.ivanguk10.coffeehouse.data.repository

import com.ivanguk10.coffeehouse.data.datasource.LocalDataSource
import com.ivanguk10.coffeehouse.data.datasource.RemoteDataSource

class Repository (
    localDataSource: LocalDataSource,
    remoteDataSource: RemoteDataSource
) {
    val local = localDataSource
    val remote = remoteDataSource
}