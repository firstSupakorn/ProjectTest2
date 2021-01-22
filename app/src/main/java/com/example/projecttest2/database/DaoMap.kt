package com.example.projecttest2.database


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.projecttest2.database.entity.IphoneData
import com.example.projecttest2.database.entity.JmartData
import com.example.projecttest2.database.entity.PromotionData

/**
 * Created by ThinkSoft on 30/12/2017.
 */
@Dao
interface DaoMap {

//----------------------------------------------
    @Query("select * from promotions")
    fun getPromotions(): List<PromotionData>
    @Query("select * from promotions")
    fun getAllPromotion(): LiveData<List<PromotionData>>
    @Query("select * from promotions where id in (:id)")
    fun getUniquePromotion(id: Int): PromotionData
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPromotion(promotion: PromotionData)

//----------------------------------------------
    @Query("select * from iphones")
    fun getAllIphone(): LiveData<List<IphoneData>>
    @Query("select * from iphones where id in (:id)")
    fun getUniqueIphone(id: Int): IphoneData
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIphone(iphone: IphoneData)

//----------------------------------------------
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJmartProduct(product: JmartData)
    @Query("select * from jmart where id in (:id)")
    fun getUniqueJmartProductId(id: Int): JmartData
    @Query("select * from jmart where modelDesc in (:modelDesc)")
    fun getUniqueJmartProduct(modelDesc: String): List<JmartData>
    @Query("select id from jmart where modelDesc in (:modelDesc) and rom in(:rom)")
    fun getId(modelDesc: String,rom: String): Int
    @Query("select * from jmart")
    fun getAllJmart(): List<JmartData>
}

