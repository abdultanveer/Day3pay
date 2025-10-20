package com.example.day3pay.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

//compile  time checks in roomdb
@Dao
interface ItemDao {
    //INSERT INTO table_name VALUES (value1, value2, value3, ...);

    @Insert
    suspend fun insert(groceryItem: Item)

    @Delete
    suspend fun delete(item: Item)

    //FLOW = suspend/coroutine functions return/generate multiple values continuosly over time

    @Query("SELECT * from item WHERE id = :id")
    fun getItem(id: Int): Flow<Item>

    @Query("SELECT * from item ORDER BY itemName ASC")
    fun getItems(): Flow<List<Item>>

}