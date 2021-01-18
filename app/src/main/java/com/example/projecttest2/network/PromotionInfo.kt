package com.example.projecttest2.network

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.room.Entity
import com.example.projecttest2.database.MapDataBase
import com.example.projecttest2.database.PromotionData
import retrofit2.Call
import retrofit2.Callback


@Entity(tableName = "promotion")
data class PromotionInfo(
        val image01 : String,
        val image02 : String,
        val image03 : String,
        val image04 : String,
        val image05 : String,
        val image06 : String,
        val image07 : String
)
class PromotionApi{

    fun getPromotion(context: Context):MutableLiveData<PromotionInfo> {
        val sessionManager = SessionManager(context)
        val token = sessionManager.fetchAuthToken("jbotToken")

        var data: MutableLiveData<PromotionInfo> = MutableLiveData()

        PromotionsApi.retrofitService.getPromotion("Bearer ${token}").enqueue(
                object : Callback<PromotionInfo> {
                    override fun onFailure(call: Call<PromotionInfo>, t: Throwable) {
                        Log.i("api", "Please Login")
                    }

                    override fun onResponse(call: Call<PromotionInfo>, response: retrofit2.Response<PromotionInfo>) {
                        if (response.isSuccessful) {
                            val json = response.body()
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