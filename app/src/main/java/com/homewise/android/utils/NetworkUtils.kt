package com.homewise.android.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by @raj on 27/12/17.
 */
object NetworkUtils{

    fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}