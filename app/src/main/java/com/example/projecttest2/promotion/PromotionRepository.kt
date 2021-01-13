package com.example.projecttest2.promotion

import android.util.Log
import com.example.projecttest2.database.DaoMap
//import com.example.projecttest2.network.PromotionsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.await

class PromotionRepository(private val database: DaoMap) {

    suspend fun refreshPromotionPage() {
        Log.i("api", "refresh promotion page")
        withContext(Dispatchers.IO) {
//            val promotionJson = PromotionsApi.retrofitService.getPromotion().await()

//            Log.i("api", "iphone json: ${promotionJson.toString()}")



        }
    }
}