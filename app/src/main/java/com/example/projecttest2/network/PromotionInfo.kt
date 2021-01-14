package com.example.projecttest2.network

import android.content.Context
import android.util.Log
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
object PromotionApi{

    fun getPromotionToDataBase(context: Context) {
        val sessionManager = SessionManager(context)
        val token = sessionManager.fetchAuthToken()

        PromotionsApi.retrofitService.getPromotion("Bearer ${token}").enqueue(
                object : Callback<PromotionInfo> {
                    override fun onFailure(call: Call<PromotionInfo>, t: Throwable) {
                        Log.i("api", "Please Login")
                    }

                    override fun onResponse(call: Call<PromotionInfo>, response: retrofit2.Response<PromotionInfo>) {
                        if (response.isSuccessful) {
                            val json = response.body()
                            insertIntoDatabase(1,json?.image01.toString(),context)
                            insertIntoDatabase(2,json?.image02.toString(),context)
                            insertIntoDatabase(3,json?.image03.toString(),context)
                            insertIntoDatabase(4,json?.image04.toString(),context)
                            insertIntoDatabase(5,json?.image05.toString(),context)
                            insertIntoDatabase(6,json?.image06.toString(),context)
                            insertIntoDatabase(7,json?.image07.toString(),context)
                        }
                        else {


                            Log.i("api", "Please Login")
                        }
                    }
                }
        )
    }



    fun insertIntoDatabase(id: Int,imageUrl: String,context: Context){
        MapDataBase.getDataBase(context).daoMap().insertPromotion(PromotionData(id,imageUrl))
        Log.i("getDatabase", MapDataBase.getDataBase(context).daoMap().getUniquePromotion(id).url!!)

    }
}