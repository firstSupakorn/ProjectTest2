package com.example.projecttest2.iphone

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.projecttest2.database.*
import com.example.projecttest2.network.getApi.JmartApiService.getJmartData
import com.example.projecttest2.network.getApi.MobileApi.getMobileSub
import com.example.projecttest2.vo.MobileSub05
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import kotlinx.coroutines.launch

class IphoneViewModel(val database: DaoMap,
    application: Application) : AndroidViewModel(application) {
    private val videosRepository = IphoneRepository(database)

    init {
        viewModelScope.launch {
            refreshJbot()
        }
    }

    fun getMobileLiveData(): LiveData<MobileSub05> {
        return getMobileSub(getApplication())
    }

    fun getProductLiveData(modelDesc: String): LiveData<JsonArray> {
        return getJmartData(getApplication(), modelDesc)
    }


    fun refreshJmart(modelDesc: String){
        // EDIT Change to listener
        getProductLiveData(modelDesc).observeForever {
            var index = 1

            for (i in it)
            {
                val regexGbMatcher = "[_]\\d{2,3}".toRegex()
                val regexModelDescMacher = "([^_]+)".toRegex()
                val jsonObject = i.asJsonObject
                val modelDescription = jsonObject.get("modelDesc")
                val itemName = jsonObject.get("modelDesc").toString()
                val rom = regexGbMatcher.findAll(modelDescription.toString()).map{it.value}.toList()[0].replace("_","")
                val filterItemName = regexModelDescMacher.findAll(itemName.toString()).map{it.value}.toList()[0].replace("\"","")
                val price = jsonObject.get("stdPrice").toString()
                val isNullPrice = jsonObject.get("stdPrice").isJsonNull
                val index = MapDataBase.getDataBase(getApplication()).daoMap().getId(modelDesc,rom)
//                val image = jsonObject.get("itemSpec").asJsonObject.get("IMAGE")

                if (modelDesc.toLowerCase() == filterItemName.toLowerCase() && !isNullPrice)
                {
                    insertIntoJmartDatabase(index,modelDesc,rom,price)
                    Log.i("jmartTest","add db ${index}   ${filterItemName}  ${filterItemName}  ${rom} ${price} ")
                }
            }
        }
    }


    fun refreshJbot() {
        Log.i("api", "refresh iphone")
        getMobileLiveData().observeForever {
            Log.i("api", "--------------------${it.toString()}")
            val jsonPromotion: JsonObject = Gson().toJsonTree(it).asJsonObject
            var index = 1
            var prevModel = ""
            for ((key, values) in jsonPromotion.entrySet()) {
                if (key!="brand") {
                    val display = values?.asJsonObject?.get("display").toString().replace("\"","")
                    val imageNormal = values?.asJsonObject?.get("imageNormal").toString().replace("\"","")
                    val model = values?.asJsonObject?.get("model").toString().replace("\"","")
                    if(display != "disable"){
                        insertIntoDatabase(index,display,imageNormal,model)
                        refreshJmart(display)
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

    fun insertIntoJmartDatabase(id: Int?,modelDesc: String, rom: String, price : String){
        if (id == 0){
            MapDataBase.getDataBase(getApplication()).daoMap().insertJmartProduct(JmartData(null,modelDesc=modelDesc,rom=rom,price=price))
        }
        else{
            MapDataBase.getDataBase(getApplication()).daoMap().insertJmartProduct(JmartData(id,modelDesc,rom,price))
        }
    }


}

data class IphoneGson (

        val display : String,
        val imageNormal : String,
        val model : String
)
