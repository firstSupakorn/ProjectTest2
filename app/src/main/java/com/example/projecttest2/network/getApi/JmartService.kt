package com.example.projecttest2.network.getApi

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.projecttest2.network.createApi.RetrofitBuilder
import com.example.projecttest2.network.token.SessionManager
import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.Callback

object JmartApiService{

    fun getJmartData(context: Context,modelDesc: String): MutableLiveData<JsonArray> {
        val sessionManager = SessionManager(context)
        val jmartToken = sessionManager.fetchAuthToken("jmartToken")

        var data: MutableLiveData<JsonArray> = MutableLiveData()

        RetrofitBuilder.JmartCreateApi.retrofitService.getProduct(modelDesc,"${jmartToken}" ).enqueue(
                object : Callback<JsonArray> {
                    override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                        Log.i("api", "Fail"+t.message)
                    }

                    override fun onResponse(call: Call<JsonArray>, response: retrofit2.Response<JsonArray>) {
                        if (response.isSuccessful) {
                            val json = response.body()
                            data.postValue(response.body())
                            Log.i("api", "Success ${json}")
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
