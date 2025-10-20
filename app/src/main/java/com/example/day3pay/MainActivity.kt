package com.example.day3pay

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import coil.load
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.math.sin

//mainActivity  = client and MyServices is serving music
class MainActivity : AppCompatActivity() {
    lateinit var mainTv:TextView
    lateinit var ivMars:ImageView
    lateinit var mainViewModel: MainViewModel
    lateinit var additionService: AdditionService


    var secsObserverphno: Observer<Int> = object : Observer<Int> {
        override fun onChanged(seconds: Int) {
            //receiving the updates/notification
            mainTv.setText(seconds.toString())
        }
    }

    var imageUrlObserver: Observer<String> = object : Observer<String> {
        override fun onChanged(url: String) {
            //receiving the updates/notification
            ivMars.load(url)
        }
    }

    // lifecycleScope.launch {  }  -- scope till activity dies
    //GlobalScope.async {  }  //scope till app is termintated
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainTv = findViewById(R.id.tvMain)
        ivMars =  findViewById(R.id.marsIv)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        //mainTv.setText(""+mainViewModel.count)
        mainTv.setText(""+mainViewModel._seconds.value)
        mainViewModel._seconds.observe(this, secsObserverphno);
        mainViewModel.imgUrl.observe(this,imageUrlObserver)


    }

    fun handleClick(view: View) {
        mainTv.setText("hello paypal -- android")
        var sIntent = Intent(this,MyService::class.java)
        sIntent.putExtra("url","https://urldownloadimage.com")
        startService(sIntent)
    }

    fun handleStop(view: View) {
        var bServiceIntent = Intent(this,AdditionService::class.java)
        unbindService(serviceConnection)

//        var sIntent = Intent(this,MyService::class.java)
//        stopService(sIntent)
    }

    fun signContractService(view: View) {  //step -1
        var bServiceIntent = Intent(this,AdditionService::class.java)
        //startService(sIntent) //setting up cateringservice--
        bindService(bServiceIntent,serviceConnection, BIND_AUTO_CREATE)  //connecting to existing service
    }

    var serviceConnection:ServiceConnection = object : ServiceConnection {  ////step 8
        override fun onServiceConnected(name: ComponentName?, lbinder: IBinder?) {
            //additionService = AdditionService()
            // im not going to instantiate this service
            //using this lbinder i can pull an instance  of  an already running AdditonnSErvice
            val binder = lbinder as AdditionService.LocalBinder //step 8a
            additionService = binder.getService()

            var result = additionService.add2nos(10,20)
            mainTv.setText("sum is $result and the score is "+additionService.getFootballScore())
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            TODO("Not yet implemented")
        }
    }

    fun incrementCount(view: View) {
        mainViewModel.getMarsPhotos()
        //mainViewModel.incrementCounter()
       // mainViewModel.startTimer()
       // mainTv.setText(""+mainViewModel._seconds)  //mainTv to observer
    }


}