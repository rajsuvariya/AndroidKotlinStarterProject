package com.homewise.android.data.remote

import android.content.Context
import javax.inject.Inject

/**
 * Created by @raj on 26/12/17.
 */
class AppApiManager @Inject constructor(context: Context) : ApiManager {
    private var mContext : Context = context
}