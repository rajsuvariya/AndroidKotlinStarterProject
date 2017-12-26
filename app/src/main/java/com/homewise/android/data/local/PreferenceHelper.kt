package com.homewise.android.data.local

import android.content.Context
import com.homewise.android.injection.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by @raj on 26/12/17.
 */
@Singleton
class PreferenceHelper @Inject constructor(@ApplicationContext context: Context) {

    val PREF_FILE_NAME = "_shared_prefs_file"

    private lateinit var mPref: ObscuredSharedPreferences

    fun clear() {
        mPref.edit().clear().apply()
    }

    fun getString(KEY: String, DEFAULT: String): String? {
        return mPref.getString(KEY, DEFAULT)
    }

    fun putString(KEY: String, VALUE: String) {
        mPref.edit().putString(KEY, VALUE).apply()
    }

    fun putDouble(KEY: String, VALUE: Double?) {
        mPref.edit().putLong(KEY, java.lang.Double.doubleToRawLongBits(VALUE!!)).apply()
    }

    fun getDouble(KEY: String, DEFAULT: Double?): Double? {
        return java.lang.Double.longBitsToDouble(mPref.getLong(KEY, java.lang.Double.doubleToLongBits(DEFAULT!!)))
    }

    fun getBoolean(KEY: String, DEFAULT: Boolean): Boolean {
        return mPref.getBoolean(KEY, DEFAULT)
    }

    fun putBoolean(KEY: String, VALUE: Boolean) {
        mPref.edit().putBoolean(KEY, VALUE).apply()
    }

    init {
        mPref = ObscuredSharedPreferences.getPrefs(context, PREF_FILE_NAME, Context.MODE_PRIVATE)
    }
}