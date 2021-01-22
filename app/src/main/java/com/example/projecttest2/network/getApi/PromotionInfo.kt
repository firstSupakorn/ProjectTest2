package com.example.projecttest2.network.getApi

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.projecttest2.network.createApi.RetrofitBuilder
import com.example.projecttest2.network.token.SessionManager
import com.example.projecttest2.vo.PromotionInfo
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Callback

class PromotionApi{

    fun getPromotion(context: Context):MutableLiveData<PromotionInfo> {
        val sessionManager = SessionManager(context)
        val token = sessionManager.fetchAuthToken("jbotToken")

        var data: MutableLiveData<PromotionInfo> = MutableLiveData()

        RetrofitBuilder.createJbotApi.getPromotion("Bearer ${token}").enqueue(
                object : Callback<PromotionInfo> {
                    override fun onFailure(call: Call<PromotionInfo>, t: Throwable) {
                        Log.i("api", "Please Login")
                    }

                    override fun onResponse(call: Call<PromotionInfo>, response: retrofit2.Response<PromotionInfo>) {
                        if (response.isSuccessful) {
                            data.postValue(response.body())
                        }
                        else {
                            Log.i("api", "Please Login")
                        }
                    }
                }
        )
        return data
    }

    }