package com.neilzheng.uther.handler

import android.app.Activity
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.SystemClock
import android.support.v4.app.NotificationCompat

/**
 * Created by Neil Zheng on 2017/6/22.
 */

class DownloadNotification(val id: Int, val context: Context) {

    private val FLAG = Notification.FLAG_INSISTENT
    internal var requestCode = SystemClock.uptimeMillis().toInt()
    private var nm: NotificationManager = context.getSystemService(Activity.NOTIFICATION_SERVICE) as NotificationManager
    private lateinit var notification: Notification
    private var cBuilder: NotificationCompat.Builder = NotificationCompat.Builder(context)

    fun notify_progress(pendingIntent: PendingIntent, smallIcon: Int,
                        ticker: String, title: String, content: String,
                        sound: Boolean, vibrate: Boolean, lights: Boolean) {
        setCompatBuilder(pendingIntent, smallIcon, ticker, title, content, sound, vibrate, lights)
    }

    /**
     * 设置在顶部通知栏中的各种信息

     * @param pendingIntent
     * *
     * @param smallIcon
     * *
     * @param ticker
     */
    private fun setCompatBuilder(pendingIntent: PendingIntent, smallIcon: Int, ticker: String,
                                 title: String, content: String, sound: Boolean, vibrate: Boolean, lights: Boolean) {
        //        // 如果当前Activity启动在前台，则不开启新的Activity。
        //        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        //        // 当设置下面PendingIntent.FLAG_UPDATE_CURRENT这个参数的时候，常常使得点击通知栏没效果，你需要给notification设置一个独一无二的requestCode
        //        // 将Intent封装进PendingIntent中，点击通知的消息后，就会启动对应的程序
        //        PendingIntent pIntent = PendingIntent.getActivity(mContext,
        //                requestCode, intent, FLAG);
        cBuilder.setContentIntent(pendingIntent)// 该通知要启动的Intent
        cBuilder.setSmallIcon(smallIcon)// 设置顶部状态栏的小图标
        cBuilder.setTicker(ticker)// 在顶部状态栏中的提示信息
        cBuilder.setContentTitle(title)// 设置通知中心的标题
        cBuilder.setContentText(content)// 设置通知中心中的内容
        cBuilder.setWhen(System.currentTimeMillis())
        /*
         * 将AutoCancel设为true后，当你点击通知栏的notification后，它会自动被取消消失,
		 * 不设置的话点击消息后也不清除，但可以滑动删除
		 */
        cBuilder.setAutoCancel(true)
        // 将Ongoing设为true 那么notification将不能滑动删除
        // notifyBuilder.setOngoing(true);
        /*
         * 从Android4.1开始，可以通过以下方法，设置notification的优先级，
		 * 优先级越高的，通知排的越靠前，优先级低的，不会在手机最顶部的状态栏显示图标
		 */
        cBuilder.priority = NotificationCompat.PRIORITY_MAX
        /*
         * Notification.DEFAULT_ALL：铃声、闪光、震动均系统默认。
		 * Notification.DEFAULT_SOUND：系统默认铃声。
		 * Notification.DEFAULT_VIBRATE：系统默认震动。
		 * Notification.DEFAULT_LIGHTS：系统默认闪光。
		 * notifyBuilder.setDefaults(Notification.DEFAULT_ALL);
		 */
        var defaults = 0
        if (sound) {
            defaults = defaults or Notification.DEFAULT_SOUND
        }
        if (vibrate) {
            defaults = defaults or Notification.DEFAULT_VIBRATE
        }
        if (lights) {
            defaults = defaults or Notification.DEFAULT_LIGHTS
        }
        cBuilder.setDefaults(defaults)
    }

    fun setProgress(maxprogress: Int, currentprogress: Int, exc: Boolean) {
        cBuilder.setProgress(maxprogress, currentprogress, exc)
        sent()
    }

    fun setProgressFinish(content: String, pendingIntent: PendingIntent) {
        cBuilder.setContentText(content)
        cBuilder.setProgress(100, 100, false)
        cBuilder.setContentIntent(pendingIntent)
        sent()
    }

    /**
     * 发送通知
     */
    fun sent() {
        notification = cBuilder.build()
        // 发送该通知
        nm.notify(id, notification)
    }

    /**
     * 根据id清除通知
     */
    fun clear() {
        // 取消通知
        nm.cancelAll()
    }

    fun cancel(id: Int) {
        nm.cancel(id)
    }
}