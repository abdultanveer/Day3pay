package com.example.day3pay

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.day3pay.network.MarsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.http.GET

/**
 * in view model hold the data to be supplied to the view[activity]
 * the data stored  in the viewmodel is agonistic of the lifecycle of that activity/view
 * let the viewmodel have biz logic
 */
class MainViewModel:ViewModel() {
    lateinit var timer: CountDownTimer

    var _seconds = MutableLiveData<Int>()//observable data
    var imgUrl  = MutableLiveData<String>()

    var TAG = MainViewModel::class.java.simpleName
    var  count = 10  //server-db

    fun  incrementCounter(){
        count++
    }

    //suspendable and resumable functions
    //coroutine -- eg launch  -- co operative routine[functions]
   // {result = 30}
    fun add(a:Int, b:Int):String{
        return "" + a + b
    }

    fun getWeather(cityName:String):String{
        return "weather deatils of "+cityName
    }


//modularizing code --functions-- code more readable
    //THREADS -- divide the code based on how much time each module/function takes


    //each instruction takes 5ns
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

//launch = coroutine -- a suspenndable funnction
     fun getMarsPhotos() {
//viewmodelscope
        GlobalScope.launch(Dispatchers.Main) {
            val listResult = MarsApi.retrofitService.getPhotos()  //this might gtake  1 sec
            imgUrl.value = listResult.get(0).imgSrc
            Log.i(TAG,listResult.get(0).imgSrc)

        }
    }


}