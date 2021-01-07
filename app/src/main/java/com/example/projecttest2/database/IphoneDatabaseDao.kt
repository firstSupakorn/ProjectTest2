package com.example.projecttest2.database
import androidx.room.*
import androidx.lifecycle.LiveData


@Dao
interface IphoneDatabaseDao{

    @Insert
    fun insert(iphone: IphoneEntity)

    @Update
    fun update(iphone: IphoneEntity)

    @Query("SELECT * from iphone_table WHERE modelId = :key")
    fun get(key: Long): IphoneEntity

    @Query("DELETE * from iphone_table")
    fun clear()

    @Query("SELECT * from iphone_table ORDER BY modelId DESC")
    fun getAllIphone(): LiveData<List<IphoneEntity>>


}