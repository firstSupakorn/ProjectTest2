package com.example.projecttest2.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [(MapData::class)],version = 1,exportSchema = false)
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

