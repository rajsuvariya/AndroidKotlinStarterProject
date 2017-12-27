package com.homewise.android.ui.base

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.StringRes
import android.widget.Toast
import com.homewise.android.HomeWiseApplication
import com.homewise.android.injection.component.DaggerFragmentComponent
import com.homewise.android.injection.component.FragmentComponent
import com.homewise.android.injection.module.FragmentModule
import com.homewise.android.utils.KeyboardUtils

/**
 * Created by @raj on 27/12/17.
 */
abstract class BaseFragment : Fragment(), MvpView {

    var baseActivity: BaseActivity? = null
        private set
    private var mFragmentComponent: FragmentComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    fun getmFragmentComponent(): FragmentComponent? {
        if (mFragmentComponent == null) {
            mFragmentComponent = DaggerFragmentComponent.builder()
                    .fragmentModule(FragmentModule(Fragment()))
                    .applicationComponent(HomeWiseApplication.get(activity).getApplictionComponent())
                    .build()
        }
        return mFragmentComponent
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        if (activity is BaseActivity) {
            this.baseActivity = activity
            //            activity.onFragmentAttached();
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            this.baseActivity = context
            //            activity.onFragmentAttached();
        }
    }

    override fun showLoading(message: String) {
        if (baseActivity != null) {
            baseActivity!!.showLoading(message)
        }
    }

    override fun hideLoading() {
        if (baseActivity != null) {
            baseActivity!!.hideLoading()
        }
    }

    override fun showToast(message: String?) {
        if (baseActivity != null) {
            baseActivity!!.showToast(message)
        }
    }

    override fun showToast(@StringRes resId: Int) {
        if (baseActivity != null) {
            baseActivity!!.showToast(resId)
        }
    }

    override fun onDetach() {
        baseActivity = null
        KeyboardUtils.hideSoftInput(activity)
        super.onDetach()
    }

    override fun hideKeyboard() {
        if (baseActivity != null) {
            baseActivity!!.hideKeyboard()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    interface Callback {

        fun onFragmentAttached(context: Context)

        fun onFragmentDetached(tag: String)
    }

    override fun invalidAuthCode() {
    }
}
