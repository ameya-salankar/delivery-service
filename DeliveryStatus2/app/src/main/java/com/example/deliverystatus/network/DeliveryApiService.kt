package com.example.deliverystatus.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "http://192.168.42.107:8080/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface DeliveryApiService {
    @GET("deliveries")
    fun getDeliveries(@Query(value = "fromTime") fromTime: String, @Query(value = "toTime") toTime: String): Call<List<DeliveryObject>>
}

object DeliveryApi {
    val retrofitApiService: DeliveryApiService by lazy {
        retrofit.create(DeliveryApiService::class.java)
    }
}
