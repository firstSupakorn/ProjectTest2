package com.example.projecttest2.network.createApi

import com.example.projecttest2.network.token.TokenInfo
import com.example.projecttest2.vo.MobileSubInfo
import com.example.projecttest2.vo.PromotionInfo
import com.example.projecttest2.vo.UserInfo
import com.google.gson.JsonArray
import com.squareup.moshi.JsonClass
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

fun PromotionContainer.asDomainModel(): List<PromotionInfo> {
    return promotions.map {
        PromotionInfo(
            image01 = it.image01,
            image02 = it.image02,
            image03 = it.image03,
            image04 = it.image04,
            image05 = it.image05,
            image06 = it.image06,
            image07 = it.image07
        )
    }
}

@JsonClass(generateAdapter = true)
data class PromotionContainer(val promotions: List<PromotionInfo>)


interface JbotApi {
    @GET("api/promotion")
    fun getPromotion(@Header("Authorization") authHeader: String): Call<PromotionInfo>

    @GET("api/promotion")
    fun getPromotions(@Header("Authorization") authHeader: String): Deferred<PromotionInfo>

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