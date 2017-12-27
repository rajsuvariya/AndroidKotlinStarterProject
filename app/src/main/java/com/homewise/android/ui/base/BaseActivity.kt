package com.homewise.android.ui.base

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.homewise.android.R

open class BaseActivity : AppCompatActivity() {

//    private lateinit var mActivityComponent : ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
//
//        mActivityComponent = DaggerApplicationComponent.builder()
//                .activityModule(ActivityModule())
//                .applicationModule((application as HomeWiseApplication).getApplictionComponent())
//                .build()
    }

//    fun getActivityComponent() : ActivityComponent {
//        return mActivityComponent
//    }
}
