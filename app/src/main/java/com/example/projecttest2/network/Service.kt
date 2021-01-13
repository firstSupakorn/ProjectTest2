package com.example.projecttest2.network



// import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import MobileSubInfo
import PromotionInfo
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


// import kotlinx.coroutines.Deferred

private const val BASE_URL = "https://fibo.jaymart.org/"


class TokenInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        //rewrite the request to add bearer token
        val newRequest: Request = chain.request().newBuilder()
                .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJuYW1laWQiOiJBZG1pbiIsIm5iZiI6MTYxMDA5NDU3MywiZXhwIjoxNjEwNjk5MzczLCJpYXQiOjE2MTAwOTQ1NzN9.Zm3ooHJxb2CaXce2tJBduv3A_dQqZj28Sw6nvcn8_un_84-sraaL4NOAiU1vQkoOQY5pL6izCMvCm7KG7LX_Dw")
                .build()
        return chain.proceed(newRequest)
    }
}



private fun getUnsafeOkHttpClient(): OkHttpClient {
    // Create a trust manager that does not validate certificate chains
    val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
        override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
        }
        override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
        }
        override fun getAcceptedIssuers() = arrayOf<X509Certificate>()
    })

    // Install the all-trusting trust manager
    val sslContext = SSLContext.getInstance("SSL")
    sslContext.init(null, trustAllCerts, java.security.SecureRandom())
    // Create an ssl socket factory with our all-trusting manager
    val sslSocketFactory = sslContext.socketFactory
    var interceptor = TokenInterceptor()

    return OkHttpClient.Builder()
            .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            .build()
}
private val client = getUnsafeOkHttpClient()

var gson = GsonBuilder()
        .setLenient()
        .create()

private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private val retrofit = Retrofit.Builder()
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(BASE_URL)
        .build()

data class UserInfo (
        val email: String,
        val password: String?
)

/**
 * A public interface that exposes the [getProperties] method
 */
interface PromotionsApiService {
    @GET("api/promotion")
    fun getPromotion(@Header("Authorization") authHeader: String): Call<JsonObject>

    @GET("api/mobileSub")
    fun getMobileSub(@Header("Authorization") authHeader: String): Call<JsonObject>

    @Headers("Content-Type: application/json")
    @POST("api/user/login")
    fun getToken(@Body userData:UserInfo): Call<TokenInfo>

}


object PromotionsApi {
    val retrofitService : PromotionsApiService by lazy {
        retrofit.create(PromotionsApiService::class.java)
    }

}

object ApiService{
    private var token = ""

    fun getToken(){
        val userInfo = UserInfo("admin@jaymart","Jaymart@2020")
        PromotionsApi.retrofitService.getToken(userInfo).enqueue(
                object : Callback<TokenInfo> {
                    override fun onFailure(call: Call<TokenInfo>, t: Throwable) {
                        Log.i("api", t.message.toString())
                    }

                    override fun onResponse(call: Call<TokenInfo>, response: retrofit2.Response<TokenInfo>) {
                        val tokenVar = response.body()
                        token = tokenVar?.token.toString()
                        Log.i("api",tokenVar?.token.toString())
                    }
                }
        )
    }

    fun getMobileSub(){
        PromotionsApi.retrofitService.getMobileSub("Bearer ${token}" ).enqueue(
                object : Callback<JsonObject> {
                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        Log.i("api", "Fail")
                    }

                    override fun onResponse(call: Call<JsonObject>, response: retrofit2.Response<JsonObject>) {
                        if (response.isSuccessful) {
                            val json = response.body()
                            val iphoneGson = Gson().fromJson(json, MobileSubInfo::class.java)
                            Log.i("api", "mobile sub: ${iphoneGson.toString()}")
                        }
                        else{
                                Log.i("api", "Please Login")
                            }

                    }
                }
    )
    }


    fun getPromotion() {
        PromotionsApi.retrofitService.getPromotion("Bearer ${token}").enqueue(
                object : Callback<JsonObject> {
                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        Log.i("api", "Fail")
                    }

                    override fun onResponse(call: Call<JsonObject>, response: retrofit2.Response<JsonObject>) {
                        if (response.isSuccessful) {
                            val json = response.body()
                            val promotionGson = Gson().fromJson(json, PromotionInfo::class.java)
                            Log.i("api", promotionGson.toString())
                        } else {
                            Log.i("api", "Please Login")
                        }
                    }
                }
        )
    }

}






