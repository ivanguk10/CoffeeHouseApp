package com.ivanguk10.coffeehouse.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivanguk10.coffeehouse.data.database.entity.HotProduct
import com.ivanguk10.coffeehouse.data.database.entity.NewsAndSalesEntity
import com.ivanguk10.coffeehouse.data.model.*
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

    private var _coffeeResponse: MutableLiveData<NetworkResult<List<CoffeeModel>>> = MutableLiveData()
    val coffeeResponse: LiveData<NetworkResult<List<CoffeeModel>>> = _coffeeResponse

    private var _teaResponse: MutableLiveData<NetworkResult<List<TeaModel>>> = MutableLiveData()
    val teaResponse: LiveData<NetworkResult<List<TeaModel>>> = _teaResponse

    private var _drinkResponse: MutableLiveData<NetworkResult<List<DrinkModel>>> = MutableLiveData()
    val drinkResponse: LiveData<NetworkResult<List<DrinkModel>>> = _drinkResponse

    private var _dessertsResponse: MutableLiveData<NetworkResult<List<DessertModel>>> = MutableLiveData()
    val dessertsResponse: LiveData<NetworkResult<List<DessertModel>>> = _dessertsResponse

    private var _altsResponse: MutableLiveData<NetworkResult<List<AltMilkModel>>> = MutableLiveData()
    val altsResponse: LiveData<NetworkResult<List<AltMilkModel>>> = _altsResponse

    fun getNews() = viewModelScope.launch(Dispatchers.IO) {
        getNewsSafeCall()
    }

    fun getSingleNews() = viewModelScope.launch(Dispatchers.IO) {
        getSingleNewsSafeCall()
    }

    fun getCoffee() = viewModelScope.launch(Dispatchers.IO) {
        getCoffeeSafeCall()
    }

    fun getTea() = viewModelScope.launch(Dispatchers.IO) {
        getTeaSafeCall()
    }

    fun getDrinks() = viewModelScope.launch(Dispatchers.IO) {
        getDrinkSafeCall()
    }

    fun getDesserts() = viewModelScope.launch(Dispatchers.IO) {
        getDessertsSafeCall()
    }

    fun getAlts() = viewModelScope.launch(Dispatchers.IO) {
        getAltsSafeCall()
    }

    private suspend fun getAltsSafeCall() {
        _altsResponse.postValue(NetworkResult.Loading())

        val response = repository.remote.getAlts()
        _altsResponse.postValue(handleAltsResponse(response))
    }

    private suspend fun getDessertsSafeCall() {
        _dessertsResponse.postValue(NetworkResult.Loading())

        val response = repository.remote.getDesserts()
        _dessertsResponse.postValue(handleDessertsResponse(response))
    }

    private suspend fun getDrinkSafeCall() {
        _drinkResponse.postValue(NetworkResult.Loading())

        val response = repository.remote.getDrink()
        _drinkResponse.postValue(handleDrinkResponse(response))
    }

    private suspend fun getTeaSafeCall() {
        _teaResponse.postValue(NetworkResult.Loading())

        val response = repository.remote.getTea()
        _teaResponse.postValue(handleTeaResponse(response))
    }

    private suspend fun getCoffeeSafeCall() {
        _coffeeResponse.postValue(NetworkResult.Loading())

        val response = repository.remote.getCoffee()
        _coffeeResponse.postValue(handleCoffeeResponse(response))
    }

    private suspend fun getSingleNewsSafeCall() {
        _singleNewsResponse.postValue(NetworkResult.Loading())

        val response = repository.remote.getNewsId(1)
        _singleNewsResponse.postValue(handleSingleNewsResponse(response))
    }

    private suspend fun getNewsSafeCall() {
        _newsResponse.postValue(NetworkResult.Loading())
        val response = repository.remote.getNews()
        _newsResponse.postValue(handleNewsResponse(response))
    }

    private fun handleAltsResponse(
        response: Response<List<AltMilkModel>>
    ): NetworkResult<List<AltMilkModel>> {
        return when {
            response.body()!!.isEmpty() -> {
                NetworkResult.Error("empty.")
            }
            response.isSuccessful -> {
                val dessert = response.body()
                NetworkResult.Success(dessert!!)
            }
            else -> {
                NetworkResult.Error(response.message())
            }
        }
    }

    private fun handleDessertsResponse(
        response: Response<List<DessertModel>>
    ): NetworkResult<List<DessertModel>> {
        return when {
            response.body()!!.isEmpty() -> {
                NetworkResult.Error("empty.")
            }
            response.isSuccessful -> {
                val dessert = response.body()
                NetworkResult.Success(dessert!!)
            }
            else -> {
                NetworkResult.Error(response.message())
            }
        }
    }

    private fun handleDrinkResponse(
        response: Response<List<DrinkModel>>
    ): NetworkResult<List<DrinkModel>> {
        return when {
            response.body()!!.isEmpty() -> {
                NetworkResult.Error("empty.")
            }
            response.isSuccessful -> {
                val drink = response.body()
                NetworkResult.Success(drink!!)
            }
            else -> {
                NetworkResult.Error(response.message())
            }
        }
    }

    private fun handleTeaResponse(
        response: Response<List<TeaModel>>
    ): NetworkResult<List<TeaModel>> {
        return when {
            response.body()!!.isEmpty() -> {
                NetworkResult.Error("empty.")
            }
            response.isSuccessful -> {
                val tea = response.body()
                NetworkResult.Success(tea!!)
            }
            else -> {
                NetworkResult.Error(response.message())
            }
        }
    }

    private fun handleCoffeeResponse(
        response: Response<List<CoffeeModel>>
    ): NetworkResult<List<CoffeeModel>> {
        return when {
            response.body()!!.isEmpty() -> {
                NetworkResult.Error("empty.")
            }
            response.isSuccessful -> {
                val coffee = response.body()
                NetworkResult.Success(coffee!!)
            }
            else -> {
                NetworkResult.Error(response.message())
            }
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