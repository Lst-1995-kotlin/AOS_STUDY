package com.junior.lst_1995.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "productId")
    var id: Int = 0,
    @ColumnInfo(name = "productName")
    var productName: String,
    var quantity: Int
)