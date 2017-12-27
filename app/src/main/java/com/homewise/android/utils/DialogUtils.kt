package com.homewise.android.utils

import android.R
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.widget.ArrayAdapter

/**
 * Created by @raj on 27/12/17.
 */

object DialogUtils {

    private val TAG = "DialogUtils"

    var mProgressDialog: ProgressDialog? = null
    private var mAlertDialog: AlertDialog? = null

    fun displayProgressDialog(context: Context?, message: String) {
        if (mProgressDialog == null && context != null) {
            mProgressDialog = ProgressDialog(context)
            mProgressDialog!!.setMessage(message)
            mProgressDialog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            mProgressDialog!!.isIndeterminate = true
            mProgressDialog!!.show()
            mProgressDialog!!.setCanceledOnTouchOutside(false)
            mProgressDialog!!.setCancelable(false)
        }
    }

    fun cancelProgressDialog() {
        if (mProgressDialog != null) {
            try {
                mProgressDialog!!.dismiss()
                mProgressDialog!!.cancel()
                mProgressDialog = null
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    fun dialogBoxWithButtons(context: Context, title: String, message: String,
                             positiveButton: String, negativeButton: String, cancelable: Boolean,
                             positiveCallback: DialogInterface.OnClickListener, negativeCallback: DialogInterface.OnClickListener) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
                .setMessage(message)
                .setCancelable(cancelable)
                .setPositiveButton(positiveButton, positiveCallback)
                .setNegativeButton(negativeButton, negativeCallback)

        mAlertDialog = builder.create()
        mAlertDialog!!.setOnShowListener { mAlertDialog!!.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(context.resources.getColor(R.color.black)) }
        mAlertDialog!!.show()

    }

    fun showSelectOptionList(context: Context, title: String, optionArray: Array<String>, itemClick: DialogInterface.OnClickListener) {
        val builderSingle = AlertDialog.Builder(context)
        builderSingle.setTitle(title)
        val adapter = ArrayAdapter(context, R.layout.simple_list_item_1, optionArray)
        builderSingle.setAdapter(adapter, itemClick)
        builderSingle.show()
    }

    fun cancelDialogBox() {
        if (mAlertDialog != null && mAlertDialog!!.isShowing) {
            mAlertDialog!!.dismiss()
        }
    }
}
