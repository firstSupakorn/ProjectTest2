package com.example.projecttest2.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.projecttest2.database.DaoMap
import com.example.projecttest2.database.DatabaseOjb
import com.example.projecttest2.database.MapDataBase
import com.example.projecttest2.database.entity.PromotionData
import com.example.projecttest2.database.entity.Url
import com.example.projecttest2.database.entity.asDomainModel
import com.example.projecttest2.network.createApi.RetrofitBuilder
import com.example.projecttest2.network.getApi.PromotionApi
import com.example.projecttest2.network.token.SessionManager
import com.example.projecttest2.vo.PromotionInfo
import com.google.gson.Gson
import com.google.gson.JsonObject
//import com.example.projecttest2.network.PromotionsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.await

class PromotionRepository(val context: Context) {

    val promotions: LiveData<List<Url>> =
            Transformations.map(MapDataBase.getDataBase(context).daoMap().getAllPromotion()) {
                it.asDomainModel()
            }

    suspend fun refreshPromotionPage() {
        withContext(Dispatchers.IO) {
            val sessionManager = SessionManager(context)
            val token = sessionManager.fetchAuthToken("jbotToken")

            val promotionInfo =  RetrofitBuilder.createJbotApi.getPromotion("Bearer ${token}").await()

            val jsonPromotion: JsonObject = Gson().toJsonTree(promotionInfo).asJsonObject
            var index = 1

            // EDIT ----------------------------------------------------
            for ((key, values) in jsonPromotion.entrySet())
            {
                insertIntoDatabase(index,values.toString().replace("\"",""))
                index = index + 1
            }
            //----------------------------------------------------
        }
    }

    // get live data
    fun getPromotionLiveData():LiveData<PromotionInfo>{
        return PromotionApi().getPromotion(context)
    }

    // insert data into database
    fun insertIntoDatabase(id: Int,imageUrl: String){
        DatabaseOjb(context).daoMap.insertPromotion(PromotionData(id, imageUrl))
        Log.i("getDatabase", "id : $id  "+MapDataBase.getDataBase(context).daoMap().getUniquePromotion(id).url!!)
    }
