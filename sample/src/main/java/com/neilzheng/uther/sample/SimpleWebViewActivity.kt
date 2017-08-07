package com.neilzheng.uther.sample

import android.os.Bundle
import android.support.v7.widget.LinearLayoutCompat
import android.view.ViewGroup
import com.neilzheng.uther.Uther

/**
 * Created by Neil Zheng on 2017/6/15.
 */

class SimpleWebViewActivity : BaseWebViewActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = LinearLayoutCompat(this@SimpleWebViewActivity)
        root.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        setContentView(root)
        initWebView()
    }

    private fun initWebView() {
        uther = Uther.with(this@SimpleWebViewActivity)
                .setTitleBarVisible(showTitleBar)
                .setProgressBarVisible(showProgressBar)
                .setReceiveTitle(receiveTitleFlag)
                .setTitle(title)
                .setUrl(url)
                .build()
    }

}