package com.mohitbhavsar.familyspotter

import android.content.Context
import android.widget.Toast


object SingleToast {
    private var mToast: Toast? = null
    fun show(context: Context?, text: String, duration: Int) {
        if (mToast != null) mToast!!.cancel()
        mToast = Toast.makeText(context, text, duration)
        with(mToast) { this!!.show() }
    }

//    fun clearToasts(){
//        if (mToast != null) mToast!!.cancel()
//    }
}