package com.example.day3pay

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * in view model hold the data to be supplied to the view[activity]
 * the data stored  in the viewmodel is agonistic of the lifecycle of that activity/view
 * let the viewmodel have biz logic
 */
class MainViewModel:ViewModel() {
    lateinit var timer: CountDownTimer

    var _seconds = MutableLiveData<Int>()//observable data

    var TAG = MainViewModel::class.java.simpleName
    var  count = 0  //server-db

    fun  incrementCounter(){
        count++
    }

    fun startTimer(){
        timer = object :CountDownTimer(10_000,1_000){
            override fun onTick(timeRemainning: Long) {
                _seconds.value = timeRemainning.toInt()
                Log.i(TAG,"seconds value ="+_seconds.value)
            }

            override fun onFinish() {
                Log.i(TAG,"timer finnished")
            }

        }.start()
    }


}