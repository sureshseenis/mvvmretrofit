package com.customer_reg.kaspon_app.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.customer_reg.kaspon_app.network.RetrofitClient
import com.customer_reg.kaspon_app.network.model.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    private val exceptionLiveData = MutableLiveData<Throwable>()
    var registerResponse = MutableLiveData<LoginRegResponse>()
    var countriesResponse = MutableLiveData<CountriesResponse>()
    var stateResponse = MutableLiveData<StatesResponse>()
    var citiesResponse = MutableLiveData<CitiesResponse>()
    var amcResponse = MutableLiveData<AmcResponse>()
    var locationResponse = MutableLiveData<LocationResponse>()
    var productResponse = MutableLiveData<ProductResponse>()
    var subProductResponse = MutableLiveData<SubProductResponse>()

    val exceptionHandler = CoroutineExceptionHandler { _, t ->
        run {
            t.printStackTrace()
            exceptionLiveData.postValue(t)
        }
    }

    init {
        getAmcDetails()
        getCountries()
        getProductDetails()
    }

    fun registerUser(hashMap: HashMap<String, Any>) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val response = RetrofitClient.apiService.registerUser(hashMap)
            if (response != null) {
                if (response.isSuccessful)
                    registerResponse.value = response.body()
                else exceptionLiveData.postValue(Throwable(response.errorBody().toString()))

            } else {
                exceptionLiveData.postValue(Throwable("Something went wrong!"))
            }
        }
    }

    private fun getCountries() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val response = RetrofitClient.apiService.getCountries()
            if (response != null) {
                if (response.isSuccessful)
                    countriesResponse.postValue(response.body())
                else exceptionLiveData.postValue(Throwable(response.errorBody().toString()))

            } else {
                exceptionLiveData.postValue(Throwable("Something went wrong!"))
            }
        }
    }


    fun getStates(country_id: Int) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val response = RetrofitClient.apiService.getStates(country_id)
            if (response != null) {
                if (response.isSuccessful)
                    stateResponse.postValue(response.body())
                else exceptionLiveData.postValue(Throwable(response.errorBody().toString()))

            } else {
                exceptionLiveData.postValue(Throwable("Something went wrong!"))
            }
        }
    }


    fun getCities(state_id: Int) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val response = RetrofitClient.apiService.getCities(state_id)
            if (response != null) {
                if (response.isSuccessful)
                    citiesResponse.postValue(response.body())
                else exceptionLiveData.postValue(Throwable(response.errorBody().toString()))

            } else {
                exceptionLiveData.postValue(Throwable("Something went wrong!"))
            }
        }
    }

    fun getLocations(city_id: Int) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val response = RetrofitClient.apiService.getLocations(city_id)
            if (response != null) {
                if (response.isSuccessful)
                    locationResponse.postValue(response.body())
                else exceptionLiveData.postValue(Throwable(response.errorBody().toString()))

            } else {
                exceptionLiveData.postValue(Throwable("Something went wrong!"))
            }
        }
    }

    fun getProductDetails() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val response = RetrofitClient.apiService.getProductDetails()
            if (response != null) {
                if (response.isSuccessful)
                    productResponse.postValue(response.body())
                else exceptionLiveData.postValue(Throwable(response.errorBody().toString()))

            } else {
                exceptionLiveData.postValue(Throwable("Something went wrong!"))
            }
        }
    }

    fun getSubProduct(product_id:Int) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val response = RetrofitClient.apiService.getSubProductDetails(product_id)
            if (response != null) {
                if (response.isSuccessful)
                    subProductResponse.postValue(response.body())
                else exceptionLiveData.postValue(Throwable(response.errorBody().toString()))

            } else {
                exceptionLiveData.postValue(Throwable("Something went wrong!"))
            }
        }
    }

    fun getAmcDetails() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val response = RetrofitClient.apiService.getAmcDetails()
            if (response != null) {
                if (response.isSuccessful)
                    amcResponse.postValue(response.body())
                else exceptionLiveData.postValue(Throwable(response.errorBody().toString()))

            } else {
                exceptionLiveData.postValue(Throwable("Something went wrong!"))
            }
        }
    }


}