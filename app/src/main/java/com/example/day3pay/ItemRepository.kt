package com.example.day3pay

import com.example.day3pay.database.Item
import com.example.day3pay.database.ItemDao
import kotlinx.coroutines.flow.Flow

class ItemRepository(private val itemDao: ItemDao) {
    suspend fun insert(item: Item) = itemDao.insert(item)
    val allItems: Flow<List<Item>> = itemDao.getItems()
}
