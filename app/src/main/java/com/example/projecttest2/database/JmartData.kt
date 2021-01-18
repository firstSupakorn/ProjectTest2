package com.example.projecttest2.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jmart")
data class JmartData (
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id : Int,
        @ColumnInfo(name = "modelDesc")
        var modelDesc: String?,
        @ColumnInfo(name = "rom")
        var rom: String?,
        @ColumnInfo(name = "price")
        var price: String?
)
