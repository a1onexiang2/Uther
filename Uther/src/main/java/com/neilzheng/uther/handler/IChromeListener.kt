package com.neilzheng.uther.handler

import android.graphics.Bitmap
import android.net.Uri
import android.os.Message
import android.view.View
import android.webkit.*

/**
 * Created by Neil Zheng on 2017/6/15.
 */

interface IChromeListener {

    fun onRequestFocus(view: WebView?): Boolean

    fun onJsAlert(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean

    fun onJsPrompt(view: WebView?, url: String?, message: String?, defaultValue: String?,
                   result: JsPromptResult?): Boolean

    fun onShowCustomView(view: View?, callback: WebChromeClient.CustomViewCallback?): Boolean

    fun onGeolocationPermissionsShowPrompt(origin: String?, callback: GeolocationPermissions.Callback?): Boolean

    fun onPermissionRequest(request: PermissionRequest?): Boolean

    fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean

    fun onPermissionRequestCanceled(request: PermissionRequest?): Boolean

    fun onShowFileChooser(webView: WebView?, filePathCallback: ValueCallback<Array<Uri>>?,
                          fileChooserParams: WebChromeClient.FileChooserParams?): Boolean

    fun onReceivedTouchIconUrl(view: WebView?, url: String?, precomposed: Boolean): Boolean

    fun onReceivedIcon(view: WebView?, icon: Bitmap?): Boolean

    fun onReceivedTitle(view: WebView?, title: String?): Boolean

    fun onProgressChanged(view: WebView?, newProgress: Int): Boolean

    fun onJsConfirm(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean

    fun getVisitedHistory(callback: ValueCallback<Array<String>>?): Boolean

    fun onGeolocationPermissionsHidePrompt(): Boolean

    fun onJsBeforeUnload(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean

    fun onHideCustomView(): Boolean

    fun onCreateWindow(view: WebView?, isDialog: Boolean, isUserGesture: Boolean, resultMsg: Message?): Boolean

    fun onCloseWindow(window: WebView?): Boolean

    fun getVideoLoadingProgressView(): View?

}
