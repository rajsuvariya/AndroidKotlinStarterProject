package com.homewise.android.ui.base

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.homewise.android.HomeWiseApplication
import com.homewise.android.R
import com.homewise.android.injection.component.ActivityComponent
import com.homewise.android.injection.component.DaggerActivityComponent
import com.homewise.android.injection.component.DaggerApplicationComponent
import com.homewise.android.injection.module.ActivityModule

open class BaseActivity : AppCompatActivity() {

    private lateinit var mActivityComponent : ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .applicationComponent((application as HomeWiseApplication).getApplictionComponent())
                .build()
    }

    fun getActivityComponent() : ActivityComponent {
        return mActivityComponent
    }
}
