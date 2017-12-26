package com.homewise.android.ui.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.homewise.android.R
import com.homewise.android.data.local.AppPreferenceManager
import com.homewise.android.data.local.PreferenceHelper
import com.homewise.android.data.local.PreferenceManager
import com.homewise.android.data.remote.ApiManager
import com.homewise.android.data.remote.AppApiManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}
