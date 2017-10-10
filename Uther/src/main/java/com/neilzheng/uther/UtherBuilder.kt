package com.neilzheng.uther

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.os.Build
import android.support.v7.widget.LinearLayoutCompat
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.support.v4.app.Fragment as SupportFragment
import android.view.View
import android.view.ViewGroup
import android.webkit.DownloadListener
import android.webkit.WebView
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.neilzheng.uther.handler.IChromeListener
import com.neilzheng.uther.handler.IUrlListener
import com.neilzheng.uther.handler.SimpleChromeListener
import com.neilzheng.uther.widget.BaseWebView
import com.neilzheng.uther.widget.DefaultProgressBar
import com.neilzheng.uther.widget.DefaultTitleBar

/**
 * Created by Neil Zheng on 2017/8/7.
 */
class UtherBuilder private constructor(val context: Context, val viewGroup: ViewGroup) {

    private var layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT)
    private var view: View? = null
    private var showTitleBar = false
    private var showProgressBar = true
    private var receiveTitle = true
    private var title: String? = null
    private var url: String? = null
    private var header: Map<String, String> = HashMap()
    private var titleBar: Toolbar? = null
    private var progressBar: ProgressBar? = null
    private var videoProgress: View? = null
    private var chromeListeners = arrayListOf<IChromeListener>()
    private var urlListeners = arrayListOf<IUrlListener>()
    private var downloadListener: DownloadListener? = null

    private constructor(activity: Activity) : this(activity, activity.findViewById(android.R.id.content) as ViewGroup)

    private constructor(fragment: Fragment) : this(
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) fragment.context else fragment.activity,
            fragment.view as ViewGroup)

    private constructor(fragment: SupportFragment) : this(fragment.context, fragment.view as ViewGroup)

    internal companion object {

        internal fun with(activity: Activity): UtherBuilder = UtherBuilder(activity)

        internal fun with(fragment: Fragment): UtherBuilder = UtherBuilder(fragment)

        internal fun with(fragment: SupportFragment): UtherBuilder = UtherBuilder(fragment)

        internal fun with(view: View): UtherBuilder {
            val result = UtherBuilder(view.context, view.parent as ViewGroup)
            result.view = view
            result.layoutParams = view.layoutParams
            return result
        }

        internal fun with(viewGroup: ViewGroup): UtherBuilder = UtherBuilder(viewGroup.context, viewGroup)

    }

    fun setLayoutParams(layoutParams: ViewGroup.LayoutParams): UtherBuilder {
        this.layoutParams = layoutParams
        return this
    }

    fun setTitleBarVisible(boolean: Boolean): UtherBuilder {
        showTitleBar = boolean
        return this
    }

    fun setTitleBar(titleBar: Toolbar): UtherBuilder {
        this.titleBar = titleBar
        showTitleBar = true
        return this
    }

    fun setProgressBarVisible(boolean: Boolean): UtherBuilder {
        showProgressBar = boolean
        return this
    }

    fun setProgressBar(progressBar: ProgressBar): UtherBuilder {
        this.progressBar = progressBar
        showProgressBar = true
        return this
    }

    fun setVideoProgress(videoProgress: View): UtherBuilder {
        this.videoProgress = videoProgress
        return this
    }

    fun setReceiveTitle(boolean: Boolean): UtherBuilder {
        receiveTitle = boolean
        return this
    }

    fun setTitle(title: String?): UtherBuilder {
        this.title = title
        return this
    }

    fun setUrl(url: String?): UtherBuilder {
        this.url = url
        return this
    }

    fun setHeader(header: Map<String, String>): UtherBuilder {
        this.header = header
        return this
    }

    fun addUrlListener(vararg urlListener: IUrlListener): UtherBuilder {
        this.urlListeners.addAll(urlListener)
        return this
    }

    fun addUrlListener(vararg chromeListener: IChromeListener): UtherBuilder {
        this.chromeListeners.addAll(chromeListener)
        return this
    }

    fun setDownloadListener(downloadListener: DownloadListener): UtherBuilder {
        this.downloadListener = downloadListener
        return this
    }

    fun build(): Uther {
        if (showTitleBar && null == titleBar) {
            titleBar = DefaultTitleBar(context)
        }
        if (showProgressBar && null == progressBar) {
            progressBar = DefaultProgressBar(context, null, android.R.attr.progressBarStyleHorizontal,
                    android.R.style.Widget_ProgressBar_Horizontal)
        }
        val webView = BaseWebView(context)
        for (item in chromeListeners) {
            webView.addChromeHandler(item)
        }
        for (item in urlListeners) {
            webView.addUrlHandler(item)
        }
        if (null != downloadListener) {
            webView.setDownloadListener(downloadListener)
        }
        val rootContainer = LinearLayoutCompat(context)
        rootContainer.orientation = LinearLayout.VERTICAL
        val webViewContainer = FrameLayout(context)
        webViewContainer.layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT)
        rootContainer.addView(webViewContainer)
        webViewContainer.addView(webView)
        val index = (0..viewGroup.childCount).firstOrNull { view == viewGroup.getChildAt(it) } ?: -1
        if (null != view && index > -1) {
            viewGroup.removeView(view)
        }
        viewGroup.addView(rootContainer, index, layoutParams)
        if (showTitleBar) {
            rootContainer.addView(titleBar, 0)
        }
        if (showProgressBar) {
            webViewContainer.addView(progressBar)
        }
        if (showProgressBar || receiveTitle) {
            webView.addChromeHandler(object : SimpleChromeListener() {
                override fun onProgressChanged(view: WebView?, newProgress: Int): Boolean {
                    if (showProgressBar) {
                        when (newProgress) {
                            100 -> progressBar!!.visibility = View.GONE
                            else -> {
                                progressBar!!.progress = newProgress
                                progressBar!!.visibility = View.VISIBLE
                            }
                        }
                    }
                    return false
                }

                override fun onReceivedTitle(view: WebView?, title: String?): Boolean {
                    if (showTitleBar && receiveTitle && !TextUtils.isEmpty(title)) {
                        titleBar!!.title = title
                    }
                    return false
                }
            })
        }
        if (!TextUtils.isEmpty(title)) {
            titleBar!!.title = title
        }
        if (!TextUtils.isEmpty(url)) {
            webView.loadUrl(url, header)
        }
        return Uther(webView)
    }

}