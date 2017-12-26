package com.homewise.android.data

import com.homewise.android.data.local.PreferenceManager
import com.homewise.android.data.remote.ApiManager

/**
 * Created by @raj on 26/12/17.
 */
interface DataManager : ApiManager, PreferenceManager {
}