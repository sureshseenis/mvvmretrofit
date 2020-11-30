package com.customer_reg.kaspon_app.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.customer_reg.kaspon_app.network.RetrofitClient
import com.customer_reg.kaspon_app.network.model.LoginRegResponse
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {


    private val exceptionLiveData = MutableLiveData<Throwable>()
    var loginResponse = MutableLiveData<LoginRegResponse>()

    val exceptionHandler = CoroutineExceptionHandler { _, t ->
        run {
            t.printStackTrace()
            exceptionLiveData.postValue(t)
        }
    }

    fun loginUser(hashMap: HashMap<String, String>) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val response = RetrofitClient.apiService.loginUser(hashMap)
            if (response != null) {
                if (response.isSuccessful)
                    loginResponse.value = response.body()
                else exceptionLiveData.postValue(Throwable(response.errorBody().toString()))

            } else {
                exceptionLiveData.postValue(Throwable("Something went wrong!"))
            }
        }
    }
}