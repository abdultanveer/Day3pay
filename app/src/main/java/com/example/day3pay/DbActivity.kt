package com.example.day3pay

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.day3pay.database.Item
import com.example.day3pay.database.ItemDao
import com.example.day3pay.database.ItemRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class DbActivity : AppCompatActivity() {
    lateinit var nameEt:EditText
    lateinit var priceEt:EditText
    lateinit var qtyEt:EditText
    lateinit var dao: ItemDao
    lateinit var tvDb:TextView
    lateinit var smsLv:ListView

    private lateinit var viewModel: DbViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_db)
        nameEt = findViewById(R.id.etItemName)
        priceEt = findViewById(R.id.etPrice)
        qtyEt = findViewById(R.id.etQty)
        tvDb = findViewById(R.id.tvDb)
        smsLv = findViewById(R.id.listView)

//        var  database = ItemRoomDatabase.getDatabase(this)
//        dao = database.itemDao()
        viewModel = ViewModelProvider(this)[DbViewModel::class.java]



    }


    override fun onStart() {
        super.onStart()

//        Uri uriSms = Uri.parse("content://sms/inbox");
//        Cursor c = context.getContentResolver().query(uriSms, null,null,null,null);
        var uriSms = Uri.parse("content://sms/inbox")
        var cursor = contentResolver.query(uriSms,null,null,null,null)
        var from =  arrayOf("body","address")
        var to = intArrayOf(android.R.id.text1,android.R.id.text2)
        var sadapter = SimpleCursorAdapter(this,android.R.layout.simple_list_item_2,cursor,from,to)
        smsLv.adapter = sadapter
    }


//RUNTIME PERMISSION

    //you can't invoke a suspendable[insert fn] in a normal fn, u can innvoke it only in a coroutine/another suspend fn
    fun saveDb(view: View) {
        var name = nameEt.text.toString()
        var price = priceEt.text.toString()
        var qty = qtyEt.text.toString()
        var item = Item(0,name,price.toDouble(),qty.toInt())
        viewModel.insert(item)
//        lifecycleScope.launch {
//            dao.insert(item)
//            dao.getItem(1)
//        }

    }
    fun getDb(view: View) {

            viewModel.allItems.observe(this) { items ->
                val displayText = items.joinToString("\n") { it.toString() }
                tvDb.text = displayText
            }

        }
    }
