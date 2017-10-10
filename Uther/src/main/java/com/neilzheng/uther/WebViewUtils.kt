package com.neilzheng.uther

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.StatFs
import java.io.File


/**
 * Created by Neil Zheng on 2017/6/21.
 */

internal class WebViewUtils {

    internal companion object {

        fun getIntentCompat(context: Context, file: File): Intent {
            val mIntent: Intent
            if (context.applicationInfo.targetSdkVersion >= Build.VERSION_CODES.N) {
                mIntent = Intent(Intent.ACTION_VIEW)
                mIntent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive")
                mIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            } else {
                mIntent = WebViewUtils.getFileIntent(file)
            }
            return mIntent
        }

        fun getFileIntent(file: File): Intent {
            val uri = Uri.fromFile(file)
            val type = WebViewUtils.getMIMEType(file)
            val intent = Intent("android.intent.action.VIEW")
            intent.addCategory("android.intent.category.DEFAULT")
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.setDataAndType(uri, type)
            return intent
        }

        private fun getMIMEType(f: File): String {
            val fName = f.name
            val end = fName.substring(fName.lastIndexOf(".") + 1, fName.length).toLowerCase()
            return when (end) {
                "pdf" -> "application/pdf"
                "m4a", "mp3", "mid", "xmf", "ogg", "wav" -> "audio/*"
                "3gp", "mp4" -> "video/*"
                "jpg", "gif", "png", "jpeg", "bmp" -> "image/*"
                "apk" -> "application/vnd.android.package-archive"
                else -> "*/*"
            }
        }

        fun checkNetwork(context: Context): Boolean {
            if(null == context.getSystemService(Context.CONNECTIVITY_SERVICE)) {
                return false
            }
            val connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = connectivity.activeNetworkInfo
            return info != null && info.isConnected
        }

        fun getAvailableStorage(): Long {
            try {
                val stat = StatFs(Environment.getExternalStorageDirectory().toString())
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    return stat.availableBlocksLong * stat.blockSizeLong
                } else {
                    return stat.availableBlocks.toLong() * stat.blockSize.toLong()
                }
            } catch (ex: RuntimeException) {
                return 0
            }
        }
    }
}
