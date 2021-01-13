package com.example.projecttest2.promotion

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.projecttest2.database.DaoMap
import kotlinx.coroutines.launch

class PromotionViewModel(
        val database: DaoMap,
        application: Application) : AndroidViewModel(application) {

    private val promotionRepository = PromotionRepository(database)

    init {
        viewModelScope.launch {
            promotionRepository.refreshPromotionPage()
        }
    }

//    fun getPromotion(){
//    PromotionsApi.retrofitService.getPromotion().enqueue(
//        object : Callback<JsonObject> {
//            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
//                Log.i("api", "Fail")
//            }
//
//            override fun onResponse(call: Call<JsonObject>, response: retrofit2.Response<JsonObject>) {
//                val promotionImageUrl = response.body()
//                Log.i("api",promotionImageUrl.toString())
//            }
//        }
//        )
//    }

}
