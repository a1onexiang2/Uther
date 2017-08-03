package com.neilzheng.uther.utils

import android.app.Activity
import android.content.Context
import android.content.Intent

/**
 * Created by Neil Zheng on 2017/6/15.
 */

fun <T: Activity> Context.startActivity(clazz: Class<T>) {
    startActivity(Intent(this, clazz))
}
