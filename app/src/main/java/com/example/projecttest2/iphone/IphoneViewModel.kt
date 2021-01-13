package com.example.projecttest2.iphone

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.projecttest2.database.DaoMap
import com.example.projecttest2.network.PromotionInfo
import com.example.projecttest2.network.PromotionsApi
import com.example.projecttest2.network.TokenInfo
import com.example.projecttest2.network.UserInfo
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback

class IphoneViewModel(val database: DaoMap,
    application: Application) : AndroidViewModel(application) {

    private val videosRepository = IphoneRepository(database)

    init {
        viewModelScope.launch {
            videosRepository.refreshIphonePage()
        }
    }

//    fun getMobileSub(){
//        PromotionsApi.retrofitService.getMobileSub().enqueue(
//            object : Callback<JsonObject> {
//                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
//                    Log.i("api", "Fail")
//                }
//
//                override fun onResponse(call: Call<JsonObject>, response: retrofit2.Response<JsonObject>) {
//                    val jsonMobileSub = response.body()
////                    Log.i("api",promotionImageUrl.toString())
//                }
//            }
//            )
//        }

//    fun getToken(){
//        val userInfo = UserInfo("admin@jaymart","Jaymart@2020")
//        PromotionsApi.retrofitService.getToken(userInfo).enqueue(
//                object : Callback<JsonObject> {
//                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
//                        Log.i("api", t.message.toString())
//                    }
//
//                    override fun onResponse(call: Call<JsonObject>, response: retrofit2.Response<JsonObject>) {
//                        val token = response.body()
//                        Log.i("api",token.toString())
//                    }
//                }
//        )
//
//    }

    }



//data class Iphone(val model: String, val ram: String)
