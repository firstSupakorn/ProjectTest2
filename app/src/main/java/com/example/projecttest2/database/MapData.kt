package com.example.projecttest2.database

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "maps")
data class MapData (
        @ColumnInfo(name = "id")
        @PrimaryKey(autoGenerate = true)
        var id : Int,
        @ColumnInfo(name = "name")
        var name: String?
)



