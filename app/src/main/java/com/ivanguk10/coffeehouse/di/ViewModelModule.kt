package com.ivanguk10.coffeehouse.di

import com.ivanguk10.coffeehouse.MyWorker
import com.ivanguk10.coffeehouse.viewmodels.CameraXViewModel
import com.ivanguk10.coffeehouse.viewmodels.ItemViewModel
import com.ivanguk10.coffeehouse.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
    viewModel {
        CameraXViewModel(get())
    }
    worker { MyWorker(get(), get(), get()) }

    viewModel { ItemViewModel(get()) }
}