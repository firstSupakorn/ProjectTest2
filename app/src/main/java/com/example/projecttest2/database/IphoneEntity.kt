package com.example.projecttest2.database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo


@Entity(tableName = "iphone_table")
data class IphoneEntity(
    @PrimaryKey(autoGenerate = true)
    var modelId: Long = 0L,

    @ColumnInfo(name = "iphone_model")
    var modelName: String = ""

)

