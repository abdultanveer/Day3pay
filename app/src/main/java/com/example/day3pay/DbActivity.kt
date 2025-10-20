package com.example.day3pay

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.day3pay.database.Item
import com.example.day3pay.database.ItemDao
import com.example.day3pay.database.ItemRoomDatabase
import kotlinx.coroutines.launch

class DbActivity : AppCompatActivity() {
    lateinit var nameEt:EditText
    lateinit var priceEt:EditText
    lateinit var qtyEt:EditText
    lateinit var dao: ItemDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_db)
        nameEt = findViewById(R.id.etItemName)
        priceEt = findViewById(R.id.etPrice)
        qtyEt = findViewById(R.id.etQty)

        var  database = ItemRoomDatabase.getDatabase(this)
        dao = database.itemDao()



    }

    //you can't invoke a suspendable[insert fn] in a normal fn, u can innvoke it only in a coroutine/another suspend fn
    fun saveDb(view: View) {
        var name = nameEt.text.toString()
        var price = priceEt.text.toString()
        var qty = qtyEt.text.toString()
        var item = Item(0,name,price.toDouble(),qty.toInt())
        lifecycleScope.launch {
            dao.insert(item)
        }

    }
    fun getDb(view: View) {}
}