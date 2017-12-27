package com.homewise.android.data.local

import javax.inject.Inject

/**
 * Created by @raj on 26/12/17.
 */
class AppPreferenceManager @Inject constructor(preferencesHelper: PreferenceHelper) : PreferenceManager {
    private var mPrefHelper: PreferenceHelper = preferencesHelper

    override fun setWelcomeMessage(msg: String) {
        mPrefHelper.putString("welcome_msg", msg)
    }

    override fun getWelcomeMessage(): String? {
        return mPrefHelper.getString("welcome_msg", "")
    }


}