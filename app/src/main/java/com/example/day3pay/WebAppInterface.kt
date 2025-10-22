package com.example.day3pay

import android.content.Context
import android.util.Log
import android.webkit.JavascriptInterface
import android.widget.Toast

/** Instantiate the interface and set the context.  */
class WebAppInterface(private val mContext: Context) {

    /** Show a toast from the web page.  */
    @JavascriptInterface
    fun showToast(toast: String) {
        Log.i("webapp","hello from javascript")
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show()
    }
}