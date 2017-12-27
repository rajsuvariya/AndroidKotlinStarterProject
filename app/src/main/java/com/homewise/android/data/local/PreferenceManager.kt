package com.homewise.android.data.local

/**
 * Created by @raj on 26/12/17.
 */
interface PreferenceManager {
    fun setWelcomeMessage(msg: String)
    fun getWelcomeMessage() : String?
}