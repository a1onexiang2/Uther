package com.neilzheng.uther

import android.app.Activity
import android.app.Fragment
import android.content.Intent
import android.support.v4.app.Fragment as SupportFragment
import android.view.View
import android.view.ViewGroup
import android.webkit.ValueCallback
import com.neilzheng.uther.handler.IChromeListener
import com.neilzheng.uther.handler.IUrlListener
import com.neilzheng.uther.widget.BaseWebView
import com.neilzheng.uther.utils.screenHeight


/**
 * Created by Neil Zheng on 2017/7/3.
 */

class Uther internal constructor(internal val webView: BaseWebView) {

    companion object {

        @JvmStatic fun with(activity: Activity): UtherBuilder = UtherBuilder.with(activity)

        @JvmStatic fun with(fragment: Fragment): UtherBuilder = UtherBuilder.with(fragment)

        @JvmStatic fun with(fragment: SupportFragment): UtherBuilder = UtherBuilder.with(fragment)

        @JvmStatic fun with(view: View): UtherBuilder = UtherBuilder.with(view)

        @JvmStatic fun with(viewGroup: ViewGroup): UtherBuilder = UtherBuilder.with(viewGroup)
    }

    fun handleBackAction(): Boolean {
        return webView.handleBackAction()
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        webView.onActivityResult(requestCode, resultCode, data)
    }

    fun callJs(js: String) {
        webView.quickCallJs(js)
    }

    fun callJs(js: String, callback: ValueCallback<String>) {
        webView.quickCallJs(js, callback)
    }

    fun quickCallJs(js: String) {
        webView.quickCallJs(js)
    }

    fun quickCallJs(js: String, callback: ValueCallback<String>?, vararg params: String) {
        webView.quickCallJs(js, callback, *params)
    }

    fun quickCallJs(js: String, vararg params: String) {
        webView.quickCallJs(js, null, *params)
    }

    fun addUrlHandler(listener: IUrlListener) {
        webView.addUrlHandler(listener)
    }

    fun addChromeHandler(listener: IChromeListener) {
        webView.addChromeHandler(listener)
    }

    fun doPause() {
        webView.doPause()
    }

    fun doResume() {
        webView.doResume()
    }

    fun doDestroy() {
        webView.doDestroy()
    }

    fun loadUrl(url: String) {
        webView.loadUrl(url)
    }

    fun reload() {
        webView.reload()
    }

    fun setSize(width: Int, height: Int) {
        val parent = webView.parent.parent as ViewGroup
        val layoutParams = parent.layoutParams
        layoutParams.width = if (width > screenHeight(webView.context)) ViewGroup.LayoutParams.MATCH_PARENT else width
        layoutParams.height = if (height > screenHeight(webView.context)) ViewGroup.LayoutParams.MATCH_PARENT else height
        parent.layoutParams = layoutParams
    }

}