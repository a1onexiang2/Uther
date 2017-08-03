package com.neilzheng.uther.handler

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.net.http.SslError
import android.os.Message
import android.text.TextUtils
import android.view.KeyEvent
import android.webkit.*
import java.util.*
import android.webkit.WebView


/**
 * Created by Neil Zheng on 2017/6/15.
 */

open class BaseWebViewClient(val webView: WebView, val context: Context) : WebViewClient() {

    private var urlHandler: UrlHandler = UrlHandler(context)
    private val urls: Stack<String> = Stack()
    private var depth = 0
    private val depths = Stack<Pair<String, Int>>()
    private var urlBefore302: String? = null
    private var isLoading = false
    private var isBacking = false

    fun addUrlListener(listener: IUrlListener) {
        urlHandler.addUrlListener(listener)
    }

    fun setListenerFinal(final: Boolean) {
        urlHandler.setListenerFinal(final)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        if (isLoading || (null != url && url.startsWith("about:"))) {
            isLoading = false
        }
        if (!urlHandler.onPageFinished(view, url)) {
            super.onPageFinished(view, url)
        }
    }

    override fun shouldOverrideKeyEvent(view: WebView?, event: KeyEvent?): Boolean {
        return urlHandler.shouldOverrideKeyEvent(view, event) || super.shouldOverrideKeyEvent(view, event)
    }

    override fun doUpdateVisitedHistory(view: WebView?, url: String?, isReload: Boolean) {
        if (!urlHandler.doUpdateVisitedHistory(view, url, isReload)) {
            super.doUpdateVisitedHistory(view, url, isReload)
        }
    }

    override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
        if (!urlHandler.onReceivedError(view, request, error)) {
            super.onReceivedError(view, request, error)
        }
    }

    override fun onReceivedLoginRequest(view: WebView?, realm: String?, account: String?, args: String?) {
        if (!urlHandler.onReceivedLoginRequest(view, realm, account, args)) {
            super.onReceivedLoginRequest(view, realm, account, args)
        }
    }

    override fun onReceivedHttpError(view: WebView?, request: WebResourceRequest?, errorResponse: WebResourceResponse?) {
        if (!urlHandler.onReceivedHttpError(view, request, errorResponse)) {
            super.onReceivedHttpError(view, request, errorResponse)
        }
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        if (!isBacking && null != url) {
            depths.push(Pair(url, depth++))
            if (isLoading && urls.isNotEmpty()) {
                urlBefore302 = urls.pop()
            }
            if (!TextUtils.isEmpty(url) && !url.equals(getLastPageUrl(), true)) {
                urls.push(url)
            } else if (!TextUtils.isEmpty(urlBefore302)) {
                urls.push(urlBefore302)
                urlBefore302 = null
            }
            isLoading = true
        }
        if (!urlHandler.onPageStarted(view, url, favicon)) {
            super.onPageStarted(view, url, favicon)
        }
    }

    override fun onScaleChanged(view: WebView?, oldScale: Float, newScale: Float) {
        if (!urlHandler.onScaleChanged(view, oldScale, newScale)) {
            super.onScaleChanged(view, oldScale, newScale)
        }
    }

    override fun onPageCommitVisible(view: WebView?, url: String?) {
        if (!urlHandler.onPageCommitVisible(view, url)) {
            super.onPageCommitVisible(view, url)
        }
    }

    override fun onUnhandledKeyEvent(view: WebView?, event: KeyEvent?) {
        if (!urlHandler.onUnhandledKeyEvent(view, event)) {
            super.onUnhandledKeyEvent(view, event)
        }
    }

    override fun onReceivedClientCertRequest(view: WebView?, request: ClientCertRequest?) {
        if (!urlHandler.onReceivedClientCertRequest(view, request)) {
            super.onReceivedClientCertRequest(view, request)
        }
    }

    override fun onReceivedHttpAuthRequest(view: WebView?, handler: HttpAuthHandler?, host: String?, realm: String?) {
        if (!urlHandler.onReceivedHttpAuthRequest(view, handler, host, realm)) {
            super.onReceivedHttpAuthRequest(view, handler, host, realm)
        }
    }

    override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
        if (!urlHandler.onReceivedSslError(view, handler, error)) {
            handler?.proceed()
            super.onReceivedSslError(view, handler, error)
        }
    }

    override fun onFormResubmission(view: WebView?, dontResend: Message?, resend: Message?) {
        if (!urlHandler.onFormResubmission(view, dontResend, resend)) {
            super.onFormResubmission(view, dontResend, resend)
        }
    }

    override fun onLoadResource(view: WebView?, url: String?) {
        if (!urlHandler.onLoadResource(view, url)) {
            super.onLoadResource(view, url)
        }
    }

    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        if (urlHandler.shouldOverrideUrlLoading(view, url)) {
            return true
        } else {
            return handleUrl(url) || super.shouldOverrideUrlLoading(view, url)
        }
    }

    private fun handleUrl(url: String?): Boolean {
        if (TextUtils.isEmpty(url)) {
            return false
        }
        if (url != null) {
            if (url.startsWith(WebView.SCHEME_TEL)) {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse(url)
                context.startActivity(intent)
                return true
            } else if (url.startsWith("sms:")) {
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data = Uri.parse(url)
                context.startActivity(intent)
                return true
            } else if (url.startsWith(WebView.SCHEME_MAILTO)) {
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data = Uri.parse(url)
                context.startActivity(intent)
                return true
            }
        }
        return false
    }

    @Synchronized private fun getLastPageUrl(): String? {
        return if (urls.isNotEmpty()) urls.peek() else null
    }

    private fun popLastPageUrl(): String? {
        return if (urls.size > 1) urls.pop() else null
    }

    private fun getBackLength(url: String): Int {
        var result = 0
        do {
            val pair = depths.pop()
            val popUrl = pair.first
            if (TextUtils.equals(url, popUrl)) {
                result = pair.second - depth
                depth = pair.second
                break
            }
        } while (depths.isNotEmpty())
        return result
    }

    fun pageGoBack(): Boolean {
        val url = popLastPageUrl()
        if (url != null) {
            isBacking = true
            webView.goBackOrForward(getBackLength(url))
            return true
        }
        return false
    }
}
