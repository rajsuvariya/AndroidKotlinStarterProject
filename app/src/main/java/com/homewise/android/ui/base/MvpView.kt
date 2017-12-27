package com.homewise.android.ui.base

import android.support.annotation.StringRes

/**
 * Created by @raj on 27/12/17.
 */
interface MvpView {
    abstract fun showLoading(message: String)

    abstract fun hideLoading()

    abstract fun showToast(@StringRes resId: Int)

    abstract fun showToast(message: String?)

    abstract fun isNetworkConnected(): Boolean

    abstract fun hideKeyboard()

    abstract fun invalidAuthCode()
}