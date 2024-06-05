package com.composeit.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "category_id")
    var categoryId: Long = 0,

    @ColumnInfo(name = "category_name") var name:String,
    @ColumnInfo(name = "category_color") var categoryColor: String

)
