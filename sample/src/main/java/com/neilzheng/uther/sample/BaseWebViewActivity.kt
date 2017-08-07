package com.neilzheng.uther.sample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.ValueCallback
import com.neilzheng.uther.Uther
import com.neilzheng.uther.UtherParams
import com.neilzheng.uther.handler.IChromeListener
import com.neilzheng.uther.handler.IUrlListener
import com.neilzheng.uther.widget.BaseWebView

/**
 * Created by Neil Zheng on 2017/6/15.
 */

abstract class BaseWebViewActivity : AppCompatActivity() {

    protected var url: String? = null
    protected var title: String? = null
    protected lateinit var uther: Uther
    protected var receiveTitleFlag = true
    protected var showTitleBar = true
    protected var showProgressBar = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initIntentData()
    }

    protected open fun initIntentData() {
        if (intent.hasExtra(UtherParams.EXTRA_URL)) {
            url = intent.getStringExtra(UtherParams.EXTRA_URL)
        }
        if(intent.hasExtra(UtherParams.EXTRA_TITLE)) {
            title = intent.getStringExtra(UtherParams.EXTRA_TITLE)
        }
        receiveTitleFlag = intent.getBooleanExtra(UtherParams.EXTRA_RECEIVE_TITLE, true)
        showTitleBar = intent.getBooleanExtra(UtherParams.EXTRA_SHOW_TITLEBAR, true)
        showProgressBar = intent.getBooleanExtra(UtherParams.EXTRA_SHOW_PROGRESSBAR, true)
    }
}