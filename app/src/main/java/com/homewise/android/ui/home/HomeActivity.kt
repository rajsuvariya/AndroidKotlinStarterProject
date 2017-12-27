package com.homewise.android.ui.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.homewise.android.R
import com.homewise.android.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_base.*
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeMvpView {
//
    @Inject
    lateinit var mPresenter : HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.activity_home, frame_layout_base, false)

        setActionBarWithBackButton()

        getActivityComponent().inject(this)
        mPresenter.onAttach(this)


        mPresenter.getWelcomeMessage()
    }
}
