package com.mahmoud_ashraf.core.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private val loggingInterceptor by lazy {
    HttpLoggingInterceptor()
        .apply { level = HttpLoggingInterceptor.Level.BODY }
}
private val networkConnectionInterceptor by lazy { NetworkConnectionInterceptor() }



private val okHttpClient by lazy {
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(networkConnectionInterceptor)
        .callTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .build()
}

val retrofit: Retrofit by lazy {
    Retrofit.Builder()
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()
}

inline fun <reified T : Any> buildApi(): T = retrofit.create(T::class.java)

const val baseUrl = ""