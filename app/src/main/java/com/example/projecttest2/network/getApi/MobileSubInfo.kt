package com.example.projecttest2.network.getApi

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.projecttest2.network.createApi.RetrofitBuilder
import com.example.projecttest2.network.token.SessionManager
import com.example.projecttest2.vo.MobileSub05
import com.example.projecttest2.vo.MobileSubInfo
import retrofit2.Call
import retrofit2.Callback


object MobileApi{
    fun getMobileSub(context: Context): MutableLiveData<MobileSub05> {

        val sessionManager = SessionManager(context)
        val token = sessionManager.fetchAuthToken("jbotToken")

        var data: MutableLiveData<MobileSub05> = MutableLiveData()
        Log.i("api", "get MobileSub")

        RetrofitBuilder.createJbotApi.getMobileSub("Bearer ${token}" ).enqueue(
                object : Callback<MobileSubInfo> {
                    override fun onFailure(call: Call<MobileSubInfo>, t: Throwable) {
                        Log.i("api", "Fail")
                    }

                    override fun onResponse(call: Call<MobileSubInfo>, response: retrofit2.Response<MobileSubInfo>) {
                        if (response.isSuccessful) {
                            val json = response.body()
                            data.postValue(response.body()?.mobileSub05)
                        }
                        else{
                            Log.i("api", "Please Login")
                        }
                    }
                }
        )
        return data
    }
}