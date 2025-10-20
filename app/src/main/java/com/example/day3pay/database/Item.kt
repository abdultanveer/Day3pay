package com.example.day3pay.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//create table  item(integer id primary key)

@Entity
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val itemName: String,
    val itemPrice: Double,
    @ColumnInfo(name = "qty")
    val quantityInStock: Int
    )
