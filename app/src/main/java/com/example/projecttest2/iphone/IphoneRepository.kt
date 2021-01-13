package com.example.projecttest2.iphone

import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.example.projecttest2.database.DaoMap
//import com.example.projecttest2.network.PromotionsApi
import com.squareup.moshi.JsonClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import retrofit2.await
import java.util.EnumSet.range

data class IphoneField (
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id : Int,
        @ColumnInfo(name = "name")
        var name: String?
)

class IphoneRepository(private val database: DaoMap) {

//    val videos: LiveData<List<Video>> =


    suspend fun refreshIphonePage() {
        Log.i("api", "refresh iphone page")
        withContext(Dispatchers.IO) {
//            val jsonObject = PromotionsApi.retrofitService.getMobileSub().await()
//            val jsonIphone = jsonObject.getAsJsonObject("mobileSub05")
//            jsonIphone.getAsJsonObject().remove("brand");
//
//
//            Log.i("api", "iphone json: ${jsonIphone.get("model")}")
//            Log.i("api", "iphone json: ${jsonIphone.size()}")


//            database.videoDao.insertAll(*playlist.asDatabaseModel())
        }
    }
}

