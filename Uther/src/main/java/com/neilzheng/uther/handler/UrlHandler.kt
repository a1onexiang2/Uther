package com.neilzheng.uther.handler

/**
 * Created by Neil Zheng on 2017/6/15.
 */


class UrlHandler(val context: android.content.Context) {

    private var mListeners: ArrayList<IUrlListener> = ArrayList()
    private var mListenerFinal = false
    private var handler = android.os.Handler(context.mainLooper)

    /**
     * add a new IUrlListener at the last of the {@link #mListeners}
     * @param listener the new listener
     */
    fun addUrlListener(listener: IUrlListener) {
        if(!mListenerFinal) mListeners.add(0, listener)
    }

    /**
     * the default value of {@link #mListenerFinal} is FALSE. You CANNOT reverse the value of it since you set it to
     * TRUE. After been set to TRUE, new {@link IUrlListener} will never be saved to the list anymore.
     * @param final the value to set
     */
    fun setListenerFinal(final: Boolean) {
        if(!mListenerFinal && final) {
            mListenerFinal = final
            mListeners.trimToSize()
        }
    }

    fun onPageFinished(view: android.webkit.WebView?, url: String?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = java.util.concurrent.FutureTask(java.util.concurrent.Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onPageFinished(view, url)) {
                        result = true
                        break
                    }
                }
                result
            })
            runOnUiThread(task)
            try {
                return task.get()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
        return false
    }

    fun shouldOverrideKeyEvent(view: android.webkit.WebView?, event: android.view.KeyEvent?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = java.util.concurrent.FutureTask(java.util.concurrent.Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.shouldOverrideKeyEvent(view, event)) {
                        result = true
                        break
                    }
                }
                result
            })
            runOnUiThread(task)
            try {
                return task.get()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
        return false
    }

    fun shouldOverrideUrlLoading(view: android.webkit.WebView?, url: String?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = java.util.concurrent.FutureTask(java.util.concurrent.Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.shouldOverrideUrlLoading(view, url)) {
                        result = true
                        break
                    }
                }
                result
            })
            runOnUiThread(task)
            try {
                return task.get()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
        return false
    }

    fun doUpdateVisitedHistory(view: android.webkit.WebView?, url: String?, isReload: Boolean): Boolean {
        if (!mListeners.isEmpty()) {
            val task = java.util.concurrent.FutureTask(java.util.concurrent.Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.doUpdateVisitedHistory(view, url, isReload)) {
                        result = true
                        break
                    }
                }
                result
            })
            runOnUiThread(task)
            try {
                return task.get()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
        return false
    }

    fun onReceivedError(view: android.webkit.WebView?, request: android.webkit.WebResourceRequest?, error: android.webkit.WebResourceError?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = java.util.concurrent.FutureTask(java.util.concurrent.Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onReceivedError(view, request, error)) {
                        result = true
                        break
                    }
                }
                result
            })
            runOnUiThread(task)
            try {
                return task.get()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
        return false
    }

    fun onReceivedLoginRequest(view: android.webkit.WebView?, realm: String?, account: String?, args: String?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = java.util.concurrent.FutureTask(java.util.concurrent.Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onReceivedLoginRequest(view, realm, account, args)) {
                        result = true
                        break
                    }
                }
                result
            })
            runOnUiThread(task)
            try {
                return task.get()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
        return false
    }

    fun onReceivedHttpError(view: android.webkit.WebView?, request: android.webkit.WebResourceRequest?,
                            errorResponse: android.webkit.WebResourceResponse?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = java.util.concurrent.FutureTask(java.util.concurrent.Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onReceivedHttpError(view, request, errorResponse)) {
                        result = true
                        break
                    }
                }
                result
            })
            runOnUiThread(task)
            try {
                return task.get()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
        return false
    }

    fun onPageStarted(view: android.webkit.WebView?, url: String?, favicon: android.graphics.Bitmap?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = java.util.concurrent.FutureTask(java.util.concurrent.Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onPageStarted(view, url, favicon)) {
                        result = true
                        break
                    }
                }
                result
            })
            runOnUiThread(task)
            try {
                return task.get()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
        return false
    }

    fun onScaleChanged(view: android.webkit.WebView?, oldScale: Float, newScale: Float): Boolean {
        if (!mListeners.isEmpty()) {
            val task = java.util.concurrent.FutureTask(java.util.concurrent.Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onScaleChanged(view, oldScale, newScale)) {
                        result = true
                        break
                    }
                }
                result
            })
            runOnUiThread(task)
            try {
                return task.get()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
        return false
    }

    fun onPageCommitVisible(view: android.webkit.WebView?, url: String?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = java.util.concurrent.FutureTask(java.util.concurrent.Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onPageCommitVisible(view, url)) {
                        result = true
                        break
                    }
                }
                result
            })
            runOnUiThread(task)
            try {
                return task.get()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
        return false
    }

    fun onUnhandledKeyEvent(view: android.webkit.WebView?, event: android.view.KeyEvent?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = java.util.concurrent.FutureTask(java.util.concurrent.Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onUnhandledKeyEvent(view, event)) {
                        result = true
                        break
                    }
                }
                result
            })
            runOnUiThread(task)
            try {
                return task.get()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
        return false
    }

    fun onReceivedClientCertRequest(view: android.webkit.WebView?, request: android.webkit.ClientCertRequest?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = java.util.concurrent.FutureTask(java.util.concurrent.Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onReceivedClientCertRequest(view, request)) {
                        result = true
                        break
                    }
                }
                result
            })
            runOnUiThread(task)
            try {
                return task.get()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
        return false
    }

    fun onReceivedHttpAuthRequest(view: android.webkit.WebView?, handler: android.webkit.HttpAuthHandler?, host: String?,
                                  realm: String?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = java.util.concurrent.FutureTask(java.util.concurrent.Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onReceivedHttpAuthRequest(view, handler, host, realm)) {
                        result = true
                        break
                    }
                }
                result
            })
            runOnUiThread(task)
            try {
                return task.get()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
        return false
    }

    fun onReceivedSslError(view: android.webkit.WebView?, handler: android.webkit.SslErrorHandler?, error: android.net.http.SslError?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = java.util.concurrent.FutureTask(java.util.concurrent.Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onReceivedSslError(view, handler, error)) {
                        result = true
                        break
                    }
                }
                result
            })
            runOnUiThread(task)
            try {
                return task.get()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
        return false
    }

    fun onFormResubmission(view: android.webkit.WebView?, dontResend: android.os.Message?, resend: android.os.Message?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = java.util.concurrent.FutureTask(java.util.concurrent.Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onFormResubmission(view, dontResend, resend)) {
                        result = true
                        break
                    }
                }
                result
            })
            runOnUiThread(task)
            try {
                return task.get()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
        return false
    }

    fun onLoadResource(view: android.webkit.WebView?, url: String?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = java.util.concurrent.FutureTask(java.util.concurrent.Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onLoadResource(view, url)) {
                        result = true
                        break
                    }
                }
                result
            })
            runOnUiThread(task)
            try {
                return task.get()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
        return false
    }

    fun runOnUiThread(task: Runnable) {
        if (Thread.currentThread() !== android.os.Looper.getMainLooper().thread) {
            handler.post(task)
        } else {
            task.run()
        }
    }

}