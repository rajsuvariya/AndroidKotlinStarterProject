package com.homewise.android.ui.base

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.homewise.android.HomeWiseApplication
import com.homewise.android.R
import com.homewise.android.injection.component.ActivityComponent
import com.homewise.android.injection.component.DaggerActivityComponent
import com.homewise.android.injection.module.ActivityModule
import com.homewise.android.utils.DialogUtils
import com.homewise.android.utils.NetworkUtils
import kotlinx.android.synthetic.main.activity_base.*

open class BaseActivity : AppCompatActivity(), MvpView {

    private lateinit var mActivityComponent : ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .applicationComponent((application as HomeWiseApplication).getApplictionComponent())
                .build()

        setSupportActionBar(toolbar)
        setNavigationDrawer(false)
        inflateNavigationFragment()

    }

    protected fun setDisplayActionBar(display: Boolean) {
        if (!display)
            toolbar.visibility = View.GONE
        else
            toolbar.visibility = View.VISIBLE
    }

    fun getActivityComponent() : ActivityComponent {
        return mActivityComponent
    }


    fun setToolbarTitle(title: String) {
        supportActionBar!!.setTitle(title)
    }

    protected fun setActionBarWithNavigationButton() {
        setNavigationDrawer(true)
        toolbar.visibility = View.VISIBLE
        val toggle = android.support.v7.app.ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string
                .navigation_drawer_close)
        drawer_layout.setDrawerListener(toggle)
        toggle.syncState()
//        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//        supportActionBar!!.setHomeAsUpIndicator(resources.getDrawable(R.drawable.ic_hamburger))
    }


    protected fun setActionBarWithBackButton() {
        setNavigationDrawer(false)
        toolbar.visibility = View.VISIBLE
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//        supportActionBar!!.setHomeAsUpIndicator(resources.getDrawable(R.drawable.navigate_back))
    }

    protected fun closeNavigationDrawer() {
        drawer_layout.closeDrawers()
    }

    protected fun toggleNavigationDrawer() {
        //this is stupid because if nav drawer is open, hamburger cant be clicked
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            closeNavigationDrawer()
        } else {
            drawer_layout.openDrawer(Gravity.LEFT)
        }
    }

    protected fun setNavigationDrawer(set: Boolean) {
        if (!set) {
            drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        } else {
            drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        }
    }

//    protected var navigationFragment: NavigationFragment

    private fun inflateNavigationFragment() {
//        navigationFragment = NavigationFragment()
//        fragmentManager.beginTransaction().replace(R.id.nav_content_frame, navigationFragment, "nav").commit()
    }

    override fun showLoading(message: String) {
        DialogUtils.displayProgressDialog(this, message)
    }

    override fun hideLoading() {
        DialogUtils.cancelProgressDialog()
    }

    override fun showToast(@StringRes resId: Int) {
        showToast(getString(resId))
    }

    override fun isNetworkConnected(): Boolean {
        return NetworkUtils.isNetworkConnected(applicationContext)
    }

    override fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun showToast(message: String?) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Some Error", Toast.LENGTH_SHORT).show()
        }
    }

    override fun invalidAuthCode() {
        Toast.makeText(this, "Invalid Authcode", Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem) : Boolean
    {
        when(item.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> {
                return false
            }
        }
    }

}
