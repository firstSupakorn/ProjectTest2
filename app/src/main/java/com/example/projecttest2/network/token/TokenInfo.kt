package com.example.projecttest2.network.token

import android.content.Context
import android.util.Log
import com.example.projecttest2.network.createApi.RetrofitBuilder
import com.example.projecttest2.vo.UserInfo
import retrofit2.Call
import retrofit2.Callback

data class TokenInfo(
        val displayName: String,
        val token: String
)

object TokenApi{
    fun getToken(context: Context){
        val sessionManager = SessionManager(context)
        val userInfo = UserInfo("admin@jaymart","Jaymart@2020")
        RetrofitBuilder.createJbotApi.getToken(userInfo).enqueue(
                object : Callback<TokenInfo> {
                    override fun onFailure(call: Call<TokenInfo>, t: Throwable) {
                        Log.i("api", t.message.toString())
                    }

                    override fun onResponse(call: Call<TokenInfo>, response: retrofit2.Response<TokenInfo>) {
                        val token = response.body()?.token.toString()
                        sessionManager.saveAuthToken(token,"jbotToken")
                        Log.i("api",token)
                    }
                }
        )
    }


    fun getJmartToken(context: Context){
        val sessionManager = SessionManager(context)
        RetrofitBuilder.JmartCreateApi.retrofitService.getJmartToken().enqueue(
                object : Callback<String> {
                    override fun onFailure(call: Call<String>, t: Throwable) {
                        Log.i("api", t.message.toString())
                    }

                    override fun onResponse(call: Call<String>, response: retrofit2.Response<String>) {
                        val token = response.body().toString()
                        sessionManager.saveAuthToken(token,"jmartToken")

                        Log.i("api","jmart Token : "+token.toString())
                    }
                }
        )
    }

    fun getToken2(context: Context) {
        // Used to check token
        val sessionManager = SessionManager(context)
        val token = sessionManager.fetchAuthToken("jbot")
        Log.i("api",token.toString())

    }
}
