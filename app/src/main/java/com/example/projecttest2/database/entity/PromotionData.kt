package com.example.projecttest2.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "promotions")
data class PromotionData (
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id : Int,
        @ColumnInfo(name = "url")
        var url: String?
)

data class Url(val url: String)

fun List<PromotionData>.asDomainModel(): List<Url> {
        return map {
                Url(
                        url = it.url.toString()
                )
        }
}