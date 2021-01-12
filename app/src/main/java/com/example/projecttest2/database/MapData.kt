package com.example.projecttest2.database

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "maps")
data class MapData (
        @PrimaryKey(autoGenerate = true)

        @ColumnInfo(name = "id")
        var id : Int,
        @ColumnInfo(name = "name")
        var name: String?
)