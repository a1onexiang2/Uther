package com.neilzheng.uther.widget

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.AttributeSet
import android.webkit.CookieManager
import android.webkit.ValueCallback
import android.webkit.WebSettings
import android.webkit.WebView
import com.neilzheng.uther.BuildConfig
import com.neilzheng.uther.handler.*

/**
 * Created by Neil Zheng on 2017/6/15.
 */

class BaseWebView : WebView {

    private val webViewChromeClient: BaseWebChromeClient = BaseWebChromeClient(this@BaseWebView, context)
    private val webViewClient: BaseWebViewClient = BaseWebViewClient(this@BaseWebView, context)
    private val webViewDownloadListener: BaseWebViewDownloadListener = BaseWebViewDownloadListener(context)
    private val lifecycle: BaseWebViewLifecycle = BaseWebViewLifecycle(this@BaseWebView)
    private val jsCaller: JsCaller = JsCaller(this@BaseWebView)

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setWebContentsDebuggingEnabled(BuildConfig.DEBUG)
        }
        initSetting()
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun initSetting() {
        settings.javaScriptEnabled = true
        settings.setSupportZoom(true)
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.builtInZoomControls = false
        settings.displayZoomControls = false
        settings.savePassword = false
        settings.setAppCacheEnabled(true)
        settings.cacheMode = WebSettings.LOAD_DEFAULT
        settings.databaseEnabled = true
        settings.setGeolocationEnabled(true)
        settings.allowContentAccess = true
        settings.pluginState = WebSettings.PluginState.ON
        settings.textZoom = 100
        settings.loadsImagesAutomatically = true
        settings.setSupportMultipleWindows(false)
        settings.blockNetworkImage = false
        settings.allowFileAccess = true
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        settings.loadWithOverviewMode = true
        settings.useWideViewPort = true
        settings.domStorageEnabled = true
        settings.setNeedInitialFocus(true)
        settings.defaultTextEncodingName = "utf-8"
        val dir = context.cacheDir.absolutePath + "/cache"
        settings.setAppCachePath(dir)
        CookieManager.getInstance().setAcceptCookie(true)
        val sdk = Build.VERSION.SDK_INT
        if (sdk >= Build.VERSION_CODES.LOLLIPOP) {
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            setLayerType(LAYER_TYPE_HARDWARE, null)
            CookieManager.getInstance().setAcceptThirdPartyCookies(this, true)
        } else if (sdk >= Build.VERSION_CODES.KITKAT) {
            setLayerType(LAYER_TYPE_HARDWARE, null)
        } else {
            settings.databasePath = context.filesDir.path;
            setLayerType(LAYER_TYPE_SOFTWARE, null)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                addJavascriptInterface(BaseJavaInterface(context), "Android")
                removeJavascriptInterface("searchBoxJavaBridge_")
                removeJavascriptInterface("accessibility")
                removeJavascriptInterface("accessibilityTraversal")
            } else {
                settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                settings.allowFileAccessFromFileURLs = false //通过 file url 加载的 Javascript 读取其他的本地文件 .建议关闭
                settings.allowUniversalAccessFromFileURLs = false//允许通过 file url 加载的 Javascript 可以访问其他的源，包括其他的文件和 http，https
            }
        }
        //缓存文件最大值
        settings.setAppCacheMaxSize(java.lang.Long.MAX_VALUE)
        setWebViewClient(webViewClient)
        setWebChromeClient(webViewChromeClient)
        setDownloadListener(webViewDownloadListener)
    }

    fun handleBackAction(): Boolean {
        return webViewClient.pageGoBack()
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        webViewChromeClient.onActivityResult(requestCode, resultCode, data)
    }

    fun callJs(js: String) {
        jsCaller.quickCallJs(js)
    }

    fun callJs(js: String, callback: ValueCallback<String>) {
        jsCaller.quickCallJs(js, callback)
    }

    fun quickCallJs(js: String) {
        jsCaller.quickCallJs(js)
    }

    fun quickCallJs(js: String, callback: ValueCallback<String>?, vararg params: String) {
        jsCaller.quickCallJs(js, callback, *params)
    }

    fun quickCallJs(js: String, vararg params: String) {
        jsCaller.quickCallJs(js, null, *params)
    }

    fun addUrlHandler(listener: IUrlListener) {
        webViewClient.addUrlListener(listener)
    }

    fun addChromeHandler(listener: IChromeListener) {
        webViewChromeClient.addChromeListener(listener)
    }

    fun doPause() {
        lifecycle.onPause()
    }

    fun doResume() {
        lifecycle.onResume()
    }

    fun doDestroy() {
        lifecycle.onDestroy()
    }
}
