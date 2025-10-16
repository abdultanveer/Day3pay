package com.example.day3pay

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log


//standalone vs bound service
//foreground [user  aware--dowloading] vs background [anndroid 8 no bg service]
//local vs remote service [runs in a diff app]
class MyService : Service() {
  lateinit  var mediaPlayer:MediaPlayer

    var  TAG = MyService::class.java.simpleName

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG,"service got created")
        mediaPlayer = MediaPlayer.create(this,R.raw.music)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
         super.onStartCommand(intent, flags, startId)
        var dataReceived = intent?.getStringExtra("url")
        Log.d(TAG,"downloading  from --"+dataReceived)
        mediaPlayer.start()
        //stopSelf()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
        Log.d(TAG,"service got destroyed")

    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}