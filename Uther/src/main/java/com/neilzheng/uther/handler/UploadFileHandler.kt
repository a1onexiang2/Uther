package com.neilzheng.uther.handler

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.text.TextUtils
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import com.neilzheng.uther.BaseWebViewActivity

/**
 * Created by Neil Zheng on 2017/6/19.
 */

class UploadFileHandler(val context: Context) {

    var activity: Activity? = null
    var callback: ValueCallback<Array<Uri>>? = null

    init {
        if(context is Activity) {
            activity = context
        }
    }

    fun chooseFileWithArrayCallback(filePathCallback: ValueCallback<Array<Uri>>?,
                                    fileChooserParams: WebChromeClient.FileChooserParams?): Boolean {
        callback = filePathCallback
        chooseFile(fileChooserParams)
        return true
    }

    private fun chooseFile(fileChooserParams: WebChromeClient.FileChooserParams?) {
        if (fileChooserParams != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity?.startActivityForResult(fileChooserParams.createIntent(), BaseWebViewActivity.Companion.REQUEST_UPLOAD_FILE)
        } else {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "*/*"
            activity?.startActivityForResult(Intent.createChooser(intent, "File Chooser"),
                    BaseWebViewActivity.Companion.REQUEST_UPLOAD_FILE)
        }
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (BaseWebViewActivity.Companion.REQUEST_UPLOAD_FILE != requestCode) {
            return
        }
        if (resultCode == Activity.RESULT_CANCELED) {
            if (callback != null) {
                callback!!.onReceiveValue(null)
            }
        }
        if (resultCode == Activity.RESULT_OK) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && callback != null) {
                val array: Array<Uri>? = handleData(data)
                callback!!.onReceiveValue(array)
            }
        }
    }

    private fun handleData(data: Intent?): Array<Uri>? {
        if (data == null) {
            return null
        }
        val target = data.dataString
        if (!TextUtils.isEmpty(target)) {
            return arrayOf(Uri.parse(target))
        }
        return null
    }

}