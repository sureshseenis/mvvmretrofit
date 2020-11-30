package com.customer_reg.kaspon_app.network

import com.customer_reg.kaspon_app.network.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("get_amc_details")
    suspend fun getAmcDetails():  Response<AmcResponse>

    @GET("get_product_details")
    suspend fun getProductDetails():  Response<ProductResponse>

    @GET("get_subproduct_details")
    suspend fun getSubProductDetails(@Query("product_id") product_id: Int):  Response<SubProductResponse>

    @GET("get_country")
    suspend fun getCountries(): Response<CountriesResponse>

    @GET("get_state")
    suspend fun getStates(@Query("country_id") country_id: Int):  Response<StatesResponse>

    @GET("get_city")
    suspend fun getCities(@Query("state_id") state_id: Int):  Response<CitiesResponse>

    @GET("get_location_details")
    suspend fun getLocations(@Query("city_id") state_id: Int):  Response<LocationResponse>

    @FormUrlEncoded
    @POST("customer_login")
    suspend fun loginUser(@FieldMap hashMap: HashMap<String, String>): Response<LoginRegResponse>

    @FormUrlEncoded
    @POST("customer_registration")
    suspend fun registerUser(@FieldMap hashMap: HashMap<String, Any>): Response<LoginRegResponse>

}