package com.example.day3pay

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.sin

//mainActivity  = client and MyServices is serving
class MainActivity : AppCompatActivity() {
    lateinit var mainTv:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainTv = findViewById(R.id.tvMain)

    }

    fun handleClick(view: View) {
        mainTv.setText("hello paypal -- android")
        var sIntent = Intent(this,MyService::class.java)
        sIntent.putExtra("url","https://urldownloadimage.com")
        startService(sIntent)
    }

    fun handleStop(view: View) {
        var sIntent = Intent(this,MyService::class.java)
        stopService(sIntent)
    }
}