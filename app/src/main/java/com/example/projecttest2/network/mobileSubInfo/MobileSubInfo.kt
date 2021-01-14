package com.example.projecttest2.network

import android.content.Context
import android.util.Log
import retrofit2.Call
import retrofit2.Callback


data class MobileSubInfo (

        val mobileSub01 : MobileSub01,
        val mobileSub02 : MobileSub02,
        val mobileSub03 : MobileSub03,
        val mobileSub04 : MobileSub04,
        val mobileSub05 : MobileSub05,
        val mobileSub06 : MobileSub06
)

object MobileApi{

    fun getMobileSub(context: Context){
        val sessionManager = SessionManager(context)
        val token = sessionManager.fetchAuthToken()
        PromotionsApi.retrofitService.getMobileSub("Bearer ${token}" ).enqueue(
                object : Callback<MobileSubInfo> {
                    override fun onFailure(call: Call<MobileSubInfo>, t: Throwable) {
                        Log.i("api", "Fail")
                    }

                    override fun onResponse(call: Call<MobileSubInfo>, response: retrofit2.Response<MobileSubInfo>) {
                        if (response.isSuccessful) {
                            val json = response.body()
                            Log.i("api", "mobile sub: ${json.toString()}")
                        }
                        else{
                            Log.i("api", "Please Login")
                        }

                    }
                }
        )
    }

}