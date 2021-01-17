package com.example.projecttest2.network

import retrofit2.Call
import retrofit2.http.*

private const val BASE_URL = "https://fibo.jaymart.org/"


interface JmartApiService {
    @GET("api/promotion")
    fun getPromotion(@Header("Authorization") authHeader: String): Call<PromotionInfo>
}