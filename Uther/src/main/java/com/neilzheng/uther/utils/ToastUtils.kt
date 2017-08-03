package com.neilzheng.uther.utils

import android.content.Context
import android.support.annotation.StringRes
import android.text.TextUtils
import android.widget.Toast
import android.support.v4.app.Fragment as SupportFragment

/**
 * Created by Neil Zheng on 2017/6/19.
 */

@Volatile private var mLastShowString: String? = null
@Volatile private var mLastShowTimestamp = 0L
private val DEFAULT_TOAST_INTERVAL = 2000L

fun showToast(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
    doShowToast(context, message, duration)
}

fun showToast(context: Context, @StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    showToast(context, context.getString(resId), duration)
}

private fun doShowToast(context: Context, message: String, duration: Int) {
    if (!isToastShowing(message)) {
        Toast.makeText(context, message, duration).show()
        mLastShowTimestamp = System.currentTimeMillis()
        mLastShowString = message
    }
}

private fun isToastShowing(string: String?): Boolean {
    return TextUtils.equals(string, mLastShowString)
            && System.currentTimeMillis() - mLastShowTimestamp <= DEFAULT_TOAST_INTERVAL
}

