package com.example.day3pay

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log

class MyService : Service() {
    val mediaPlayer = MediaPlayer.create(this,R.raw.music)

    var  TAG = MyService::class.java.simpleName

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG,"service got created")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
         super.onStartCommand(intent, flags, startId)
        var dataReceived = intent?.getStringExtra("url")
        Log.d(TAG,"downloading  from --"+dataReceived)
        mediaPlayer.start()
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