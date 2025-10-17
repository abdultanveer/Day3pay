package com.example.day3pay

import androidx.lifecycle.ViewModel

/**
 * in view model hold the data to be supplied to the view[activity]
 * the data stored  in the viewmodel is agonistic of the lifecycle of that activity/view
 * let the viewmodel have biz logic
 */
class MainViewModel:ViewModel() {
    var  count = 0  //server-db

    fun  incrementCounter(){
        count++
    }
}