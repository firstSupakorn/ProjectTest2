package com.example.projecttest2.network



// import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import android.util.Log
import com.squareup.moshi.Moshi
import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import java.io.BufferedInputStream
import java.io.FileInputStream
import java.io.InputStream
import java.security.KeyStore
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.TrustManagerFactory
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

var interceptor = TokenInterceptor()


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

    return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            .build()
}
private val client = getUnsafeOkHttpClient()

private val retrofit = Retrofit.Builder()
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

/**
 * A public interface that exposes the [getProperties] method
 */
interface PromotionsApiService {
    @GET("api/promotion")
    fun getPromotion(): Call<PromotionInfo>
}


object PromotionsApi {
    val retrofitService : PromotionsApiService by lazy {
        retrofit.create(PromotionsApiService::class.java)
    }

}


class RestApiService {
    fun getPromotion(){
        val retrofit =  retrofit.create(PromotionsApiService::class.java)
        retrofit.getPromotion().enqueue(
                object : Callback<PromotionInfo> {
                    override fun onFailure(call: Call<PromotionInfo>, t: Throwable) {
                        Log.i("api","Fail")
                    }
                    override fun onResponse(call: Call<PromotionInfo>, response: retrofit2.Response<PromotionInfo>) {
                        val promotionImageUrl = response.body()
                        Log.i("api",promotionImageUrl.toString())

                    }
                }
        )
    }
}