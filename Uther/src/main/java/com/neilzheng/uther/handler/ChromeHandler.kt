package com.neilzheng.uther.handler

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import android.webkit.*
import java.util.concurrent.Callable
import java.util.concurrent.FutureTask

/**
 * Created by Neil Zheng on 2017/6/15.
 */


class ChromeHandler(val context : Context) {

    private var mListeners: ArrayList<IChromeListener> = ArrayList()
    private var mListenerFinal = false
    private var handler = Handler(context.mainLooper)

    /**
     * add a new IUrlListener at the last of the {@link #mListeners}
     * @param listener the new listener
     */
    fun addUrlListener(listener: IChromeListener) {
        if (!mListenerFinal) mListeners.add(0, listener)
    }

    /**
     * the default value of {@link #mListenerFinal} is FALSE. You CANNOT reverse the value of it since you set it to
     * TRUE. After been set to TRUE, new {@link IChromeListener} will never be saved to the list anymore.
     * @param final the value to set
     */
    fun setListenerFinal(final: Boolean) {
        if (!mListenerFinal && final) {
            mListenerFinal = final
            mListeners.trimToSize()
        }
    }

    fun onRequestFocus(view: WebView?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = FutureTask(Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onRequestFocus(view)) {
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

    fun onJsAlert(view: WebView?, url: String?, message: String?, jsResult: JsResult?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = FutureTask(Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onJsAlert(view, url, message, jsResult)) {
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

    fun onJsPrompt(view: WebView?, url: String?, message: String?, defaultValue: String?,
                   jsPrompResult: JsPromptResult?): Boolean {if (!mListeners.isEmpty()) {
            val task = FutureTask(Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onJsPrompt(view, url, message, defaultValue, jsPrompResult)) {
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

    fun onShowCustomView(view: View?, callback: WebChromeClient.CustomViewCallback?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = FutureTask(Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onShowCustomView(view, callback)) {
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

    fun onGeolocationPermissionsShowPrompt(origin: String?, callback: GeolocationPermissions.Callback?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = FutureTask(Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onGeolocationPermissionsShowPrompt(origin, callback)) {
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

    fun onPermissionRequest(request: PermissionRequest?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = FutureTask(Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onPermissionRequest(request)) {
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

    fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = FutureTask(Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onConsoleMessage(consoleMessage)) {
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

    fun onPermissionRequestCanceled(request: PermissionRequest?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = FutureTask(Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onPermissionRequestCanceled(request)) {
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

    fun onShowFileChooser(webView: WebView?, filePathCallback: ValueCallback<Array<Uri>>?,
                          fileChooserParams: WebChromeClient.FileChooserParams?): Boolean {if (!mListeners.isEmpty()) {
            val task = FutureTask(Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onShowFileChooser(webView, filePathCallback, fileChooserParams)) {
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

    fun onReceivedTouchIconUrl(view: WebView?, url: String?, precomposed: Boolean): Boolean {
        if (!mListeners.isEmpty()) {
            val task = FutureTask(Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onReceivedTouchIconUrl(view, url, precomposed)) {
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

    fun onReceivedIcon(view: WebView?, icon: Bitmap?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = FutureTask(Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onReceivedIcon(view, icon)) {
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

    fun onReceivedTitle(view: WebView?, title: String?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = FutureTask(Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onReceivedTitle(view, title)) {
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

    fun onProgressChanged(view: WebView?, newProgress: Int): Boolean {
        if (!mListeners.isEmpty()) {
            val task = FutureTask(Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onProgressChanged(view, newProgress)) {
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

    fun onJsConfirm(view: WebView?, url: String?, message: String?, jsResult: JsResult?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = FutureTask(Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onJsConfirm(view, url, message, jsResult)) {
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

    fun getVisitedHistory(callback: ValueCallback<Array<String>>?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = FutureTask(Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.getVisitedHistory(callback)) {
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

    fun onGeolocationPermissionsHidePrompt(): Boolean {
        if (!mListeners.isEmpty()) {
            val task = FutureTask(Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onGeolocationPermissionsHidePrompt()) {
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

    fun onJsBeforeUnload(view: WebView?, url: String?, message: String?, jsResult: JsResult?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = FutureTask(Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onJsBeforeUnload(view, url, message, jsResult)) {
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

    fun onHideCustomView(): Boolean {
        if (!mListeners.isEmpty()) {
            val task = FutureTask(Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onHideCustomView()) {
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

    fun onCreateWindow(view: WebView?, isDialog: Boolean, isUserGesture: Boolean, resultMsg: Message?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = FutureTask(Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onCreateWindow(view, isDialog, isUserGesture, resultMsg)) {
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

    fun onCloseWindow(window: WebView?): Boolean {
        if (!mListeners.isEmpty()) {
            val task = FutureTask(Callable<Boolean> {
                var result = false
                for (listener in mListeners) {
                    if (listener.onCloseWindow(window)) {
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

    fun getVideoLoadingProgressView(): View? {
        if (!mListeners.isEmpty()) {
            val task = FutureTask(Callable<View> {
                var view: View? = null
                for (listener in mListeners) {
                    view = listener.getVideoLoadingProgressView()
                    if (null != view) {
                        break
                    }
                }
                view
            })
            runOnUiThread(task)
            try {
                return task.get()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
        return null
    }

    fun runOnUiThread(task: Runnable) {
        if (Thread.currentThread() !== Looper.getMainLooper().thread) {
            handler.post(task)
        } else {
            task.run()
        }
    }

}