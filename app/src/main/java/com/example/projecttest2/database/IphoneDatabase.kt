package com.example.projecttest2.database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room

import java.security.AccessControlContext


@Database(entities = [IphoneEntity::class], version = 1, exportSchema = false)
abstract class IphoneDatabase : RoomDatabase() {

    abstract val IphoneDatabaseDao:IphoneDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: IphoneDatabase? = null


       fun getInstance(context: Context): IphoneDatabase{
           synchronized(this){
               var instance = INSTANCE

               if (instance == null) {
                   instance = Room.databaseBuilder(
                       context.applicationContext,
                       IphoneDatabase::class.java,
                       "iphone_database"
                   )
                       .fallbackToDestructiveMigration()
                       .build()
                   INSTANCE = instance
               }
               return instance
           }
       }
    }
}