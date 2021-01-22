package com.example.projecttest2.promotion

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.projecttest2.database.DaoMap
import com.example.projecttest2.database.MapDataBase
import com.example.projecttest2.database.entity.PromotionData
import com.example.projecttest2.network.getApi.PromotionApi
import com.example.projecttest2.repository.PromotionRepository
import com.example.projecttest2.vo.PromotionInfo
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.launch


class PromotionViewModel(
        val database: DaoMap,
        application: Application) : AndroidViewModel(application) {


    private val promotionRepository = PromotionRepository( application )
    val promotions = promotionRepository.promotions


    init {
        viewModelScope.launch {
            Log.i("liveCycle","promotion viewmodel")
//            refreshPromotion()
            promotionRepository.refreshPromotionPage()
            }
        }

    val promotionListLiveData = promotionRepository.promotions


    fun refreshPromotion() {
        Log.i("getDatabase", "refresh promotion")
        promotionRepository.getPromotionLiveData().observeForever {

        val jsonPromotion: JsonObject = Gson().toJsonTree(it).asJsonObject
        var index = 1
        for ((key, values) in jsonPromotion.entrySet())
        {
            promotionRepository.insertIntoDatabase(index,values.toString().replace("\"",""))
            index = index + 1
        }

        }
    }



}
