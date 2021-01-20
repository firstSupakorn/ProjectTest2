package com.example.projecttest2.promotion

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.projecttest2.database.DaoMap
import com.example.projecttest2.database.MapDataBase
import com.example.projecttest2.database.PromotionData
import com.example.projecttest2.network.getApi.PromotionApi
import com.example.projecttest2.repository.PromotionRepository
import com.example.projecttest2.vo.PromotionInfo
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.launch


class PromotionViewModel(
        val database: DaoMap,
        application: Application) : AndroidViewModel(application) {


    private val promotionRepository = PromotionRepository(database)
    val promotions = promotionRepository.promotions

    init {
        viewModelScope.launch {
            Log.i("getDatabase", "init view model")

            refreshPromotion()
            }
        }

    fun getPromotionLiveData():LiveData<PromotionInfo>{
        return PromotionApi().getPromotion(getApplication())
    }

    fun refreshPromotion() {
        Log.i("getDatabase", "refresh promotion")
        getPromotionLiveData().observeForever {
        val jsonPromotion: JsonObject = Gson().toJsonTree(it).asJsonObject
        var index = 1
        for ((key, values) in jsonPromotion.entrySet())
        {
            insertIntoDatabase(index,values.toString().replace("\"",""))
            index = index + 1
        }
        }
    }

    fun insertIntoDatabase(id: Int,imageUrl: String){
        MapDataBase.getDataBase(getApplication()).daoMap().insertPromotion(PromotionData(id,imageUrl))
        Log.i("getDatabase", "id : $id  "+MapDataBase.getDataBase(getApplication()).daoMap().getUniquePromotion(id).url!!)

    }


}
