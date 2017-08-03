package com.neilzheng.uther.handler

import android.content.Context

/**
 * Created by Neil Zheng on 2017/6/22.
 */

data class DownloadBean(val notificationId: Int, val url: String, val context: Context,
                        val file: java.io.File, val length: Long)