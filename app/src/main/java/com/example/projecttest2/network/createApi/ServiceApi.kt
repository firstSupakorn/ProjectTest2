package com.example.projecttest2.network.createApi

import com.example.projecttest2.network.token.TokenInfo
import com.example.projecttest2.vo.MobileSubInfo
import com.example.projecttest2.vo.PromotionInfo
import com.example.projecttest2.vo.UserInfo
import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.http.*

interface JbotApi {
    @GET("api/promotion")
    fun getPromotion(@Header("Authorization") authHeader: String): Call<PromotionInfo>

    @GET("api/mobileSub")
    fun getMobileSub(@Header("Authorization") authHeader: String): Call<MobileSubInfo>

    @Headers("Content-Type: application/json")
    @POST("api/user/login")
    fun getToken(@Body userData: UserInfo): Call<TokenInfo>
}

interface JmartApi{
    @GET("jmb-service/v1/japi/items/filter/1068/1100000042")
    fun getProduct(@Query("modelDesc") modelDesc: String, @Header("X-Auth-Token") authHeader: String): Call<JsonArray>

    @GET("jmb-service/v1/japi/generate/token/IFU_JROBOT")
    fun getJmartToken(): Call<String>
}