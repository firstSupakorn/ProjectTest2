package com.example.projecttest2.promotion

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.projecttest2.database.DaoMap
import com.example.projecttest2.database.PromotionData
import com.example.projecttest2.database.Url
import com.example.projecttest2.database.asDomainModel
import com.example.projecttest2.network.*
import com.google.gson.Gson
import com.google.gson.JsonObject
//import com.example.projecttest2.network.PromotionsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Callback
import retrofit2.await

class PromotionRepository(private val database: DaoMap) {


//    val promotions: LiveData<List<PromotionData>> = database.getAllPromotion(){
//        it.asDomainModel()
//    }

    val promotions: LiveData<List<Url>> =
            Transformations.map(database.getAllPromotion()) {
                it.asDomainModel()
            }

    suspend fun refreshPromotionPage() {
        withContext(Dispatchers.IO) {
        }
    }



}