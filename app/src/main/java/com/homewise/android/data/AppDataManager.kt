package com.homewise.android.data

import android.content.Context
import com.homewise.android.data.local.PreferenceManager
import com.homewise.android.data.remote.ApiManager
import com.homewise.android.injection.ApplicationContext
import javax.inject.Inject

/**
 * Created by @raj on 26/12/17.
 */
class AppDataManager @Inject constructor(preferenceManager: PreferenceManager, apiManager: ApiManager) : DataManager {

    private val mPreferenceManager : PreferenceManager = preferenceManager
    private val mApiManager : ApiManager = apiManager

    override fun setWelcomeMessage(msg: String) {
        mPreferenceManager.setWelcomeMessage(msg)
    }

    override fun getWelcomeMessage(): String? {
        return mPreferenceManager.getWelcomeMessage()
    }

}