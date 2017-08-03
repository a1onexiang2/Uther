package com.neilzheng.uther.handler

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.FrameLayout

/**
 * Created by Neil Zheng on 2017/6/21.
 */

class WebVideoHandler(val webView: WebView, context: Context) {

    private var activity: Activity? = null
    private var movieView: View? = null
    private var movieParentView: ViewGroup? = null
    private var mCallback: WebChromeClient.CustomViewCallback? = null

    init {
        if(context is Activity) {
            activity = context
        }
    }

    fun onShowCustomView(view: View?, callback: WebChromeClient.CustomViewCallback?) {
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        if (movieView != null) {
            callback?.onCustomViewHidden()
            return
        }
        webView.visibility = View.GONE
        if (movieParentView == null) {
            val mDecorView = activity?.window?.decorView as FrameLayout
            movieParentView = FrameLayout(activity)
            movieParentView!!.setBackgroundColor(Color.BLACK)
            mDecorView.addView(movieParentView)
        }
        this.mCallback = callback
        movieView = view
        movieParentView!!.addView(movieView)
        movieParentView!!.visibility = View.VISIBLE
    }

    fun onHideCustomView() {
        if (movieView == null) {
            return
        }
        if (activity?.requestedOrientation != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            activity!!.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        movieView!!.visibility = View.GONE
        if (movieParentView != null && movieView != null) {
            movieParentView!!.removeView(movieView)
        }
        if (movieParentView != null) {
            movieParentView!!.visibility = View.GONE
        }
        if (this.mCallback != null) {
            mCallback!!.onCustomViewHidden()
        }
        this.movieView = null
        webView.visibility = View.VISIBLE
    }

    fun isVideoState(): Boolean {
        return movieView != null
    }

    fun event(): Boolean {
        if (isVideoState()) {
            onHideCustomView()
            return true
        } else {
            return false
        }
    }
}
