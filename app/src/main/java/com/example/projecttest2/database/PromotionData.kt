package com.example.projecttest2.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "promotions")
data class PromotionData (
        @PrimaryKey(autoGenerate = true)

        @ColumnInfo(name = "url")
        var url: String?
)
