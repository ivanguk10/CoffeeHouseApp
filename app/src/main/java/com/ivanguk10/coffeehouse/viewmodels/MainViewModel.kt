package com.ivanguk10.coffeehouse.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivanguk10.coffeehouse.data.database.entity.HotProduct
import com.ivanguk10.coffeehouse.data.database.entity.NewsAndSalesEntity
import com.ivanguk10.coffeehouse.data.repository.Repository
import com.ivanguk10.coffeehouse.data.util.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel (
    private val repository: Repository
): ViewModel() {

//    Room
    fun insertNews(newsAndSalesEntity: NewsAndSalesEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.insertNews(newsAndSalesEntity)
        }
    }

    fun insertHotProduct(hotProduct: HotProduct) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.insertHotProducts(hotProduct)
        }
    }

    val readNewsAndSales: LiveData<List<NewsAndSalesEntity>> = repository.local.readAllNews()
    val readHotProducts: LiveData<List<HotProduct>> = repository.local.readAllHotProducts()


//    Network
    private var _newsResponse: MutableLiveData<NetworkResult<List<NewsAndSalesEntity>>> = MutableLiveData()
    val newsResponse: LiveData<NetworkResult<List<NewsAndSalesEntity>>> get() = _newsResponse

    private var _singleNewsResponse: MutableLiveData<NetworkResult<NewsAndSalesEntity>> = MutableLiveData()
    val singleNewsResponse: LiveData<NetworkResult<NewsAndSalesEntity>> get() = _singleNewsResponse

    private var _newNews: MutableLiveData<Response<List<NewsAndSalesEntity>>> = MutableLiveData()
    val newNews: LiveData<Response<List<NewsAndSalesEntity>>> = _newNews

    fun getNews() = viewModelScope.launch(Dispatchers.IO) {
        getNewsSafeCall()
    }

    fun getSingleNews() = viewModelScope.launch(Dispatchers.IO) {
        getSingleNewsSafeCall()
    }

    private suspend fun getSingleNewsSafeCall() {
        _singleNewsResponse.postValue(NetworkResult.Loading())

        val response = repository.remote.getNewsId(1)
        _singleNewsResponse.postValue(handleSingleNewsResponse(response))
    }

    private suspend fun getNewsSafeCall() {
        _newsResponse.postValue(NetworkResult.Loading())
        try {
            val response = repository.remote.getNews()
            _newsResponse.postValue(handleNewsResponse(response))
        } catch (e: Exception) {
            _newsResponse.postValue(NetworkResult.Error("exception."))
        }

    }

    private fun handleNewsResponse(
        response: Response<List<NewsAndSalesEntity>>
    ): NetworkResult<List<NewsAndSalesEntity>>? {
        return when {
            response.body()!!.isEmpty() -> {
                NetworkResult.Error("empty.")
            }
            response.isSuccessful -> {
                val news = response.body()
                NetworkResult.Success(news!!)
            }
            else -> {
                NetworkResult.Error(response.message())
            }
        }
    }

    private fun handleSingleNewsResponse(
        response: Response<NewsAndSalesEntity>
    ): NetworkResult<NewsAndSalesEntity>? {
        return when {
            response.isSuccessful -> {
                val news = response.body()
                NetworkResult.Success(news!!)
            }
            else -> {
                NetworkResult.Error(response.message())
            }
        }
    }


}