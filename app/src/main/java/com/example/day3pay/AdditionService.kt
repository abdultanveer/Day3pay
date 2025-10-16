package com.example.day3pay

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class AdditionService : Service() {
    private val lbinder = LocalBinder() //binder =  a  pipe b/w activity & service

    fun add2nos(a:Int,b:Int):Int{
        return a + b
    }

    fun  getFootballScore():String{
        return "latest score is"+3
    }

    override fun onBind(intent: Intent): IBinder {
      return  lbinder
    }

    inner class LocalBinder : Binder() {
        // Return this instance of LocalService so clients can call public methods.
        fun getService(): AdditionService = this@AdditionService
    }
}