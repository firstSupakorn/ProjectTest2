package com.example.projecttest2.promotion

import android.content.Context
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.projecttest2.database.DaoMap
import com.example.projecttest2.network.ApiService
import com.example.projecttest2.network.PromotionApi.getPromotionToDataBase
import com.example.projecttest2.network.PromotionsApi
import com.example.projecttest2.network.SessionManager
import com.example.projecttest2.network.TokenInfo
//import com.example.projecttest2.network.PromotionsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Callback
import retrofit2.await

class PromotionRepository(private val database: DaoMap,val context: Context) {

    suspend fun refreshPromotionPage() {
        withContext(Dispatchers.IO) {
            getPromotionToDataBase(context)
        }
    }
}