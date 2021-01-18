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

}

