package com.example.projecttest2.network.createApi
import com.google.gson.GsonBuilder
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


fun getUnsafeOkHttpClient(): OkHttpClient {
    // Create a trust manager that does not validate certificate chains
    val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager
    {
        override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
        override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
        override fun getAcceptedIssuers() = arrayOf<X509Certificate>()
    })

    // Install the all-trusting trust manager
    val sslContext = SSLContext.getInstance("SSL")
    sslContext.init(null, trustAllCerts, java.security.SecureRandom())

    // Create an ssl socket factory with our all-trusting manager
    val sslSocketFactory = sslContext.socketFactory

    return OkHttpClient.Builder()
            .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            .build()
}


object RetrofitBuilder {

    private const val JBOT_URL = "https://fibo.jaymart.org/"
    private const val JMART_URL =  "http://jmb-master.jventures.co.th/"

    val createJbotApi : JbotApi by lazy {
        val retrofit = createRetrofit(JBOT_URL)
        retrofit.create(JbotApi::class.java)
    }

    object JmartCreateApi {
        val retrofitService : JmartApi by lazy {
            val retrofit = createRetrofit(JMART_URL)
            retrofit.create(JmartApi::class.java)
        }
    }
}


fun createRetrofit(baseUrl: String): Retrofit{
    val client = getUnsafeOkHttpClient()

    var gson = GsonBuilder()
            .setLenient()
            .create()
    val retrofit = Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(baseUrl)
            .build()
    return retrofit

}





