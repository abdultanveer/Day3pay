package com.example.day3pay

import android.app.Application
import android.provider.Settings.Global
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.day3pay.database.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.Flow

class DbViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ItemRepository

    val allItems: LiveData<List<Item>>

    init {
        val dao = (application as ItemApplication).database.itemDao()
        repository = ItemRepository(dao)
        allItems = repository.allItems.asLiveData()
    }

    fun insert(item: Item) = GlobalScope.launch(Dispatchers.IO) {
        repository.insert(item)
    }
}
