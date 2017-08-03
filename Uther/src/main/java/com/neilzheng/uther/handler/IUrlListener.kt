package com.neilzheng.uther.handler

import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Message
import android.view.KeyEvent
import android.webkit.*

/**
 * Created by Neil Zheng on 2017/6/15.
 */

interface IUrlListener {

    fun onPageFinished(view: WebView?, url: String?): Boolean

    fun shouldOverrideKeyEvent(view: WebView?, event: KeyEvent?): Boolean

    fun doUpdateVisitedHistory(view: WebView?, url: String?, isReload: Boolean): Boolean

    fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?): Boolean

    fun onReceivedLoginRequest(view: WebView?, realm: String?, account: String?, args: String?): Boolean

    fun onReceivedHttpError(view: WebView?, request: WebResourceRequest?, errorResponse: WebResourceResponse?): Boolean

    fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?): Boolean

    fun onScaleChanged(view: WebView?, oldScale: Float, newScale: Float): Boolean

    fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean

    fun onPageCommitVisible(view: WebView?, url: String?): Boolean

    fun onUnhandledKeyEvent(view: WebView?, event: KeyEvent?): Boolean

    fun onReceivedClientCertRequest(view: WebView?, request: ClientCertRequest?): Boolean

    fun onReceivedHttpAuthRequest(view: WebView?, handler: HttpAuthHandler?, host: String?, realm: String?): Boolean

    fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?): Boolean

    fun onFormResubmission(view: WebView?, dontResend: Message?, resend: Message?): Boolean

    fun onLoadResource(view: WebView?, url: String?): Boolean

}
