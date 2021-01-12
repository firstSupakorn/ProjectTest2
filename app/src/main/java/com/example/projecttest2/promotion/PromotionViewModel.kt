package com.example.projecttest2.promotion

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.projecttest2.database.DaoMap
import com.example.projecttest2.iphone.IphoneRepository
import com.example.projecttest2.network.PromotionInfo
import com.example.projecttest2.network.PromotionsApi
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback

class PromotionViewModel(
        val database: DaoMap,
        application: Application) : AndroidViewModel(application) {

    private val promotionRepository = PromotionRepository(database)

    init {
        viewModelScope.launch {
            promotionRepository.refreshPromotionPage()
        }
    }

    fun getPromotion(){
    PromotionsApi.retrofitService.getPromotion().enqueue(
        object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.i("api", "Fail")
            }

            override fun onResponse(call: Call<JsonObject>, response: retrofit2.Response<JsonObject>) {
                val promotionImageUrl = response.body()
                Log.i("api",promotionImageUrl.toString())
            }
        }
        )
    }

}
