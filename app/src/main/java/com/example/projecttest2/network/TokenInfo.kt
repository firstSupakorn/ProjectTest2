package com.example.projecttest2.network

import android.content.Context
import android.util.Log
import com.google.gson.annotations.SerializedName
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
        PromotionsApi.retrofitService.getToken(userInfo).enqueue(
                object : Callback<TokenInfo> {
                    override fun onFailure(call: Call<TokenInfo>, t: Throwable) {
                        Log.i("api", t.message.toString())
                    }

                    override fun onResponse(call: Call<TokenInfo>, response: retrofit2.Response<TokenInfo>) {
                        val token = response.body()?.token.toString()
                        sessionManager.saveAuthToken(token)
                        Log.i("api",token)
                    }
                }
        )
    }

    fun getToken2(context: Context) {
        // Used to check token
        val sessionManager = SessionManager(context)
        val token = sessionManager.fetchAuthToken()
        Log.i("api",token.toString())

    }
}
