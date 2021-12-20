package com.ivanguk10.coffeehouse.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivanguk10.coffeehouse.data.database.entity.NewsAndSalesEntity
import com.ivanguk10.coffeehouse.data.model.*
import com.ivanguk10.coffeehouse.data.repository.Repository
import com.ivanguk10.coffeehouse.data.util.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class ItemViewModel(
    private val repository: Repository
): ViewModel() {

    private var _coffeeResponse: MutableLiveData<NetworkResult<CoffeeModel>> = MutableLiveData()
    val coffeeResponse: LiveData<NetworkResult<CoffeeModel>> = _coffeeResponse

    private var _teaResponse: MutableLiveData<NetworkResult<TeaModel>> = MutableLiveData()
    val teaResponse: LiveData<NetworkResult<TeaModel>> = _teaResponse

    private var _drinkResponse: MutableLiveData<NetworkResult<DrinkModel>> = MutableLiveData()
    val drinkResponse: LiveData<NetworkResult<DrinkModel>> = _drinkResponse

    private var _dessertsResponse: MutableLiveData<NetworkResult<DessertModel>> = MutableLiveData()
    val dessertsResponse: LiveData<NetworkResult<DessertModel>> = _dessertsResponse

    private var _altsResponse: MutableLiveData<NetworkResult<AltMilkModel>> = MutableLiveData()
    val altsResponse: LiveData<NetworkResult<AltMilkModel>> = _altsResponse


    fun getSingleCoffee(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        getSingleCoffeeSafeCall(id)
    }

    fun getSingleTea(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        getSingleTeaSafeCall(id)
    }

    fun getSingleDrink(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        getSingleDrinkSafeCall(id)
    }

    fun getSingleDessert(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        getSingleDessertSafeCall(id)
    }

    fun getSingleAlt(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        getSingleAltSafeCall(id)
    }

    private suspend fun getSingleAltSafeCall(id: Int) {
        _altsResponse.postValue(NetworkResult.Loading())

        val response = repository.remote.getAltId(id)
        _altsResponse.postValue(handleSingleAltResponse(response))
    }

    private suspend fun getSingleDessertSafeCall(id: Int) {
        _dessertsResponse.postValue(NetworkResult.Loading())

        val response = repository.remote.getDessertId(id)
        _dessertsResponse.postValue(handleSingleDessertResponse(response))
    }

    private suspend fun getSingleDrinkSafeCall(id: Int) {
        _drinkResponse.postValue(NetworkResult.Loading())

        val response = repository.remote.getDrinkId(id)
        _drinkResponse.postValue(handleSingleDrinkResponse(response))
    }

    private suspend fun getSingleTeaSafeCall(id: Int) {
        _teaResponse.postValue(NetworkResult.Loading())

        val response = repository.remote.getTeaId(id)
        _teaResponse.postValue(handleSingleTeaResponse(response))
    }

    private suspend fun getSingleCoffeeSafeCall(id: Int) {
        _coffeeResponse.postValue(NetworkResult.Loading())

        val response = repository.remote.getCoffeeId(id)
        _coffeeResponse.postValue(handleSingleCoffeeResponse(response))
    }

    private fun handleSingleAltResponse(
        response: Response<AltMilkModel>
    ): NetworkResult<AltMilkModel> {
        return when {
            response.isSuccessful -> {
                val dessert = response.body()
                NetworkResult.Success(dessert!!)
            }
            else -> {
                NetworkResult.Error(response.message())
            }
        }
    }

    private fun handleSingleDessertResponse(
        response: Response<DessertModel>
    ): NetworkResult<DessertModel> {
        return when {
            response.isSuccessful -> {
                val dessert = response.body()
                NetworkResult.Success(dessert!!)
            }
            else -> {
                NetworkResult.Error(response.message())
            }
        }
    }

    private fun handleSingleDrinkResponse(
        response: Response<DrinkModel>
    ): NetworkResult<DrinkModel> {
        return when {
            response.isSuccessful -> {
                val drink = response.body()
                NetworkResult.Success(drink!!)
            }
            else -> {
                NetworkResult.Error(response.message())
            }
        }
    }

    private fun handleSingleTeaResponse(
        response: Response<TeaModel>
    ): NetworkResult<TeaModel> {
        return when {
            response.isSuccessful -> {
                val tea = response.body()
                NetworkResult.Success(tea!!)
            }
            else -> {
                NetworkResult.Error(response.message())
            }
        }
    }

    private fun handleSingleCoffeeResponse(
        response: Response<CoffeeModel>
    ): NetworkResult<CoffeeModel> {
        return when {
            response.isSuccessful -> {
                val coffee = response.body()
                NetworkResult.Success(coffee!!)
            }
            else -> {
                NetworkResult.Error(response.message())
            }
        }
    }
}