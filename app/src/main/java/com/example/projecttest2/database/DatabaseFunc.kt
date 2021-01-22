package com.example.projecttest2.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.projecttest2.database.entity.PromotionData


fun getAllpromotionObj(context: Context): LiveData<List<PromotionData>>
{
    return MapDataBase.getDataBase(context).daoMap().getAllPromotion()
}

class DatabaseOjb(val context: Context) {

    var daoMap =  MapDataBase.getDataBase(context).daoMap()
}