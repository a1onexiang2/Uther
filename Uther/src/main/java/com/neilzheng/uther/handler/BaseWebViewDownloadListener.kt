package com.neilzheng.uther.handler

import android.content.Context
import android.os.Environment
import android.text.TextUtils
import android.webkit.DownloadListener
import com.neilzheng.uther.UtherConfig
import com.neilzheng.uther.WebViewUtils
import java.io.File

/**
 * Created by Neil Zheng on 2017/6/22.
 */

class BaseWebViewDownloadListener(val context: Context): DownloadListener {

    var downloadId = 0

    override fun onDownloadStart(url: String, userAgent: String,
                                 contentDisposition: String, mimeType: String, contentLength: Long) {
        val mFile = getFile(contentDisposition, url)
        if (mFile != null && mFile.exists() && mFile.length() >= contentLength) {
            val mIntent = WebViewUtils.getIntentCompat(context, mFile)
            context.startActivity(mIntent)
            return
        }
        if (mFile != null) {
            DownloadTask(DownloadBean(downloadId++, url, context, mFile, contentLength)).execute()
        }
    }

    private fun getFile(contentDisposition: String, url: String): java.io.File? {
        try {
            var filename = ""
            if (!TextUtils.isEmpty(contentDisposition) && contentDisposition.contains("filename") && !contentDisposition.endsWith("filename")) {
                val position = contentDisposition.indexOf("filename")
                filename = contentDisposition.substring(position + 1)
            }
            if (TextUtils.isEmpty(filename) && !TextUtils.isEmpty(url) && !url.endsWith("/")) {
                val p = url.lastIndexOf("/")
                if (p != -1)
                    filename = url.substring(p + 1)
                if (filename.contains("?")) {
                    val index = filename.indexOf("?")
                    filename = filename.substring(0, index)
                }
            }
            if (TextUtils.isEmpty(filename)) {
                filename = System.currentTimeMillis().toString() + ""
            }
            val mFile = java.io.File(Environment.getExternalStorageDirectory().absolutePath + File.separator
                    + UtherConfig.DOWNLOAD_PATH, filename)
            if (!mFile.exists())
                mFile.createNewFile()
            return mFile
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

}
