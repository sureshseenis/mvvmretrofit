package com.customer_reg.kaspon_app.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

object RetrofitClient {
    val apiService: ApiService by lazy {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://dev.kaspontech.com:81/")
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return@lazy retrofit.create(ApiService::class.java)
    }

    private fun getClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .addNetworkInterceptor(HeaderInterceptor())
                .connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .retryOnConnectionFailure(true)
                .build()
        client.connectionPool.evictAll()
        return client
    }

    class HeaderInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val builder = chain.request().newBuilder()
            builder.addHeader("X-Requested-With", "XMLHttpRequest")
            return chain.proceed(builder.build())
        }
    }

}