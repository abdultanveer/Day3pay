package com.example.day3pay

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
//server  app
class AdditionService : Service() {   //step 2
    private val lbinder = LocalBinder() ////step 5
    //binder =  a  pipe b/w activity & service

 private val aidlBinder = object : IAddPaypal.Stub(){
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
      return  aidlBinder
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

/*
steps
1. enable aidl in buildfeatures of build.gradle [module]
2. select app folder rt click - new - aidl file- name it as IAddPaypal
3. added sum2nos(int a,int b) method in the aidl file
4. in AdditionService created a  aidlBinder  and returned it in onBind
5.in manifest i added an intent filter
 */