package com.example.day3pay

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
//server  app
class AdditionService : Service() {   //step 2
    private val lbinder = LocalBinder() ////step 5
    //binder =  a  pipe b/w activity & service

 private val aidlBinder = object : IAddPaypal.Stub(){ //----C
     override fun sum2nos(a: Int, b: Int): Int {
         return  a +  b
     }
 }

    fun add2nos(a:Int,b:Int):Int{ //step 3
        return a + b
    }

    fun  getFootballScore():String{//step 3a
        return "latest score is"+3
    }

    override fun onBind(intent: Intent): IBinder {
      return  aidlBinder   //---- D
        //lbinder ////step 6
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    inner class LocalBinder : Binder() {//step 4
        // Return this instance of LocalService so clients can call public methods.
        fun getService(): AdditionService = this@AdditionService
    }
}

