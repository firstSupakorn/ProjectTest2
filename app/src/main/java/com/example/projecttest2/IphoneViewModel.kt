package com.example.projecttest2

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projecttest2.database.DaoMap
import com.example.projecttest2.network.PromotionInfo
import com.example.projecttest2.network.PromotionsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IphoneViewModel(
        val database: DaoMap,
        application: Application) : AndroidViewModel(application) {

    fun getPromotion(){
        PromotionsApi.retrofitService.getPromotion().enqueue(
            object : Callback<PromotionInfo> {
                override fun onFailure(call: Call<PromotionInfo>, t: Throwable) {
                    Log.i("api", "Fail")
                }

                override fun onResponse(call: Call<PromotionInfo>, response: retrofit2.Response<PromotionInfo>) {
                    val promotionImageUrl = response.body()
                    Log.i("api",promotionImageUrl.toString())
                }
            }
            )
        }

    }



//data class Iphone(val model: String, val ram: String)
