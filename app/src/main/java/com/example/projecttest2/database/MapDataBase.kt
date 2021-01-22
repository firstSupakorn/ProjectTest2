package com.example.projecttest2.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.projecttest2.database.entity.IphoneData
import com.example.projecttest2.database.entity.JmartData
import com.example.projecttest2.database.entity.PromotionData

@Database(entities = [(PromotionData::class),(IphoneData::class),(JmartData::class)],version = 3,exportSchema = false)
abstract class MapDataBase : RoomDatabase() {
    companion object {
        private var INSTANCE: MapDataBase? = null
        fun getDataBase(context: Context): MapDataBase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, MapDataBase::class.java, "map-db")
                        .allowMainThreadQueries().build()
            }
            return INSTANCE as MapDataBase
        }
    }
    abstract fun daoMap(): DaoMap
}




