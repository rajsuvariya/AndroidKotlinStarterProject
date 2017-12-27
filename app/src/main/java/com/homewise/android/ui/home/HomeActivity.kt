package com.homewise.android.ui.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.homewise.android.R
import com.homewise.android.ui.base.BaseActivity
import javax.inject.Inject

class HomeActivity : BaseActivity() {
//
    @Inject
    lateinit var mPresenter : HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        getActivityComponent().inject(this)

        mPresenter.getWelcomeMessage()
    }
}
