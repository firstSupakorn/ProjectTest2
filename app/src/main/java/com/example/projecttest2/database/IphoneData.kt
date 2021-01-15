package com.example.projecttest2.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "iphones")
data class IphoneData (
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id : Int,
        @ColumnInfo(name = "display")
        var display: String?,
        @ColumnInfo(name = "url")
        var imageUrl: String?,
        @ColumnInfo(name = "model")
        var model: String?
)
