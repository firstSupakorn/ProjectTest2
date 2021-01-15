package com.example.projecttest2.database


import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Created by ThinkSoft on 30/12/2017.
 */
@Dao
interface DaoMap {
    /*@Query("select * from maps")
    fun getAllMap():Flowable<List<MapData>>*/
    @Query("select * from maps")
    fun getAllMap():List<MapData>
    @Query("select * from maps where id in (:id)")
    fun getMap(id: Int): MapData
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMap(map: MapData)
    @Update
    fun updateMap(map: MapData)


    //----------------------------------------------
    @Query("select * from promotions")
    fun getAllPromotion(): LiveData<List<PromotionData>>
    @Query("select * from promotions where id in (:id)")
    fun getUniquePromotion(id: Int): PromotionData
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPromotion(promotion: PromotionData)

//    @Query("SELECT COUNT(id) FROM table WHERE is_checked = 1")
//    fun getCount(): Int

//----------------------------------------------
    @Query("select * from iphones")
    fun getAllIphone(): LiveData<List<IphoneData>>
    @Query("select * from iphones where id in (:id)")
    fun getUniqueIphone(id: Int): IphoneData
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIphone(iphone: IphoneData)
}

