package com.neilzheng.uther.handler

import android.graphics.Bitmap
import android.net.Uri
import android.os.Message
import android.view.View
import android.webkit.*

/**
 * Created by Neil Zheng on 2017/6/15.
 */

abstract class SimpleChromeListener : IChromeListener {

    override fun onRequestFocus(view: WebView?): Boolean = false

    override fun onJsAlert(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean = false

    override fun onJsPrompt(view: WebView?, url: String?, message: String?, defaultValue: String?, result:
    JsPromptResult?): Boolean = false

    override fun onShowCustomView(view: View?, callback: WebChromeClient.CustomViewCallback?): Boolean = false

    override fun onGeolocationPermissionsShowPrompt(origin: String?, callback: GeolocationPermissions.Callback?):
            Boolean = false

    override fun onPermissionRequest(request: PermissionRequest?): Boolean = false

    override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean = false

    override fun onPermissionRequestCanceled(request: PermissionRequest?): Boolean = false

    override fun onShowFileChooser(webView: WebView?, filePathCallback: ValueCallback<Array<Uri>>?,
                                   fileChooserParams: WebChromeClient.FileChooserParams?): Boolean = false

    override fun onReceivedTouchIconUrl(view: WebView?, url: String?, precomposed: Boolean): Boolean = false

    override fun onReceivedIcon(view: WebView?, icon: Bitmap?): Boolean = false

    override fun onReceivedTitle(view: WebView?, title: String?): Boolean = false

    override fun onProgressChanged(view: WebView?, newProgress: Int): Boolean = false

    override fun onJsConfirm(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean = false

    override fun getVisitedHistory(callback: ValueCallback<Array<String>>?): Boolean = false

    override fun onGeolocationPermissionsHidePrompt(): Boolean = false

    override fun onJsBeforeUnload(view: WebView?, url: String?, message: String?,
                                  result: JsResult?): Boolean = false

    override fun onHideCustomView(): Boolean = false

    override fun onCreateWindow(view: WebView?, isDialog: Boolean, isUserGesture: Boolean,
                                resultMsg: Message?): Boolean = false

    override fun onCloseWindow(window: WebView?): Boolean = false

    override fun getVideoLoadingProgressView(): View? = null
}
