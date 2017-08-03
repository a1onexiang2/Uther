package com.neilzheng.uther.handler

import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Message
import android.view.KeyEvent
import android.webkit.*

/**
 * Created by Neil Zheng on 2017/6/15.
 */

abstract class SimpleUrlListener : IUrlListener {

    override fun shouldOverrideKeyEvent(view: WebView?, event: KeyEvent?): Boolean = false

    override fun doUpdateVisitedHistory(view: WebView?, url: String?, isReload: Boolean): Boolean = false

    override fun onReceivedError(view: WebView?, request: WebResourceRequest?,
                                 error: WebResourceError?): Boolean = false

    override fun onReceivedLoginRequest(view: WebView?, realm: String?, account: String?,
                                        args: String?): Boolean = false

    override fun onReceivedHttpError(view: WebView?, request: WebResourceRequest?,
                                     errorResponse: WebResourceResponse?): Boolean = false

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?): Boolean = false

    override fun onScaleChanged(view: WebView?, oldScale: Float, newScale: Float): Boolean = false

    override fun onPageCommitVisible(view: WebView?, url: String?): Boolean = false

    override fun onUnhandledKeyEvent(view: WebView?, event: KeyEvent?): Boolean = false

    override fun onReceivedClientCertRequest(view: WebView?, request: ClientCertRequest?): Boolean = false

    override fun onReceivedHttpAuthRequest(view: WebView?, handler: HttpAuthHandler?, host: String?,
                                           realm: String?): Boolean = false

    override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?): Boolean = false

    override fun onFormResubmission(view: WebView?, dontResend: Message?, resend: Message?): Boolean = false

    override fun onLoadResource(view: WebView?, url: String?): Boolean = false

}
