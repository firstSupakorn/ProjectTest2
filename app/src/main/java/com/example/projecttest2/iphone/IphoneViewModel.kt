package com.example.projecttest2.iphone

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.example.projecttest2.database.DaoMap
import com.example.projecttest2.database.IphoneData
import com.example.projecttest2.database.MapDataBase
import com.example.projecttest2.database.PromotionData
import com.example.projecttest2.network.*
import com.example.projecttest2.network.MobileApi.getMobileSub
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import java.lang.Exception

class IphoneViewModel(val database: DaoMap,
    application: Application) : AndroidViewModel(application) {

    private val videosRepository = IphoneRepository(database)

    init {
        viewModelScope.launch {
            refreshIphone()
        }
    }

    fun getMobileLiveData(): LiveData<MobileSub05> {
        Log.i("api", "get live data")
        return getMobileSub(getApplication())
    }

    fun refreshIphone() {
        Log.i("api", "refresh iphone")
        getMobileLiveData().observeForever {
            Log.i("api", "--------------------${it.toString()}")

            val jsonPromotion: JsonObject = Gson().toJsonTree(it).asJsonObject
            var index = 1
            for ((key, values) in jsonPromotion.entrySet()) {
//                Log.i("api","key: ${key} value: ${values}")
                if (key!="brand") {
                    val display = values?.asJsonObject?.get("display").toString().replace("\"","")
                    val imageNormal = values?.asJsonObject?.get("imageNormal").toString().replace("\"","")
                    val model = values?.asJsonObject?.get("model").toString().replace("\"","")
                    if(display != "disable"){
                        insertIntoDatabase(index,display,imageNormal,model)
                        index = index + 1
                    }
                }
            }
        }
    }

    fun insertIntoDatabase(id: Int,display: String?, imageUrl:String?, model:String?){
        MapDataBase.getDataBase(getApplication()).daoMap().insertIphone(IphoneData(id,display,imageUrl,model))
        Log.i("getDatabase", "id : $id  "+ MapDataBase.getDataBase(getApplication()).daoMap().getUniqueIphone(id).display!!+ MapDataBase.getDataBase(getApplication()).daoMap().getUniqueIphone(id).imageUrl!!+
        MapDataBase.getDataBase(getApplication()).daoMap().getUniqueIphone(id).model!!)
    }
}

data class IphoneGson (

        val display : String,
        val imageNormal : String,
        val model : String
)
