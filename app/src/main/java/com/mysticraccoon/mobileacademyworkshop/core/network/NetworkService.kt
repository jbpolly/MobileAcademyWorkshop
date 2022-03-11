package com.mysticraccoon.mobileacademyworkshop.core.network

import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.mysticraccoon.mobileacademyworkshop.core.utils.FoodTrackerConstants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val logging = HttpLoggingInterceptor().apply {
    setLevel(HttpLoggingInterceptor.Level.BASIC)
}

val clientBuilder = OkHttpClient.Builder()
    .retryOnConnectionFailure(true)
    .connectTimeout(30, TimeUnit.SECONDS)
    .readTimeout(30, TimeUnit.SECONDS)
    .addInterceptor(logging)

val client = clientBuilder.build()


object Network {
    // Configure retrofit to parse JSON and use coroutines
    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(FoodTrackerConstants.base_api_url)
        .client(client)
        .addCallAdapterFactory(NetworkResponseAdapterFactory())
        //.addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val retrofit = retrofitBuilder.create(FoodTrackerAPI::class.java)
}