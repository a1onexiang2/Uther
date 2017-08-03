package com.neilzheng.uther.handler

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.JavascriptInterface


/**
 * Created by Neil Zheng on 2017/6/20.
 */

internal class BaseJavaInterface(val context: Context) {

    @JavascriptInterface
    fun dial(phone: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone))
        context.startActivity(intent)
    }



}
