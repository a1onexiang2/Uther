package com.neilzheng.uther.utils

import java.io.Closeable
import java.io.IOException

/**
 * Created by Neil Zheng on 2017/6/22.
 */

fun Closeable.closeSafely() {
    try {
        close()
    } catch (ignored: IOException) {
    } finally {
        try {
            close()
        } catch (ignored: IOException) {
        }
    }
}