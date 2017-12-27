package com.homewise.android.ui.home

import android.util.Log
import com.homewise.android.data.DataManager
import javax.inject.Inject

/**
 * Created by @raj on 27/12/17.
 */
class HomePresenter @Inject constructor(dataManager: DataManager) {
    private val mDataManager = dataManager
    fun getWelcomeMessage() {
        mDataManager.setWelcomeMessage("Welcome")
        Log.d("injectiontest", mDataManager.getWelcomeMessage())
    }

}