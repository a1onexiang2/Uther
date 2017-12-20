package com.neilzheng.uther.sample

import com.neilzheng.uther.Uther
import com.neilzheng.uther.UtherParams

/**
 * Created by Neil Zheng on 2017/7/4.
 */

abstract class BaseWebViewFragment : BaseFragment() {

    protected var url: String? = null
    protected var title: String? = null
    protected lateinit var uther: Uther
    protected var receiveTitleFlag = true
    protected var showTitleBar = true
    protected var showProgressBar = true

    override fun initIntentData() {
        if (arguments!!.containsKey(UtherParams.EXTRA_URL)) {
            url = arguments!!.getString(UtherParams.EXTRA_URL)
        }
        if (arguments!!.containsKey(UtherParams.EXTRA_TITLE)) {
            title = arguments!!.getString(UtherParams.EXTRA_TITLE)
        }
        receiveTitleFlag = arguments!!.getBoolean(UtherParams.EXTRA_RECEIVE_TITLE, true)
        showTitleBar = arguments!!.getBoolean(UtherParams.EXTRA_SHOW_TITLEBAR, true)
        showProgressBar = arguments!!.getBoolean(UtherParams.EXTRA_SHOW_PROGRESSBAR, true)
    }

    override fun initData() {
        uther = Uther.with(this@BaseWebViewFragment)
                .setTitleBarVisible(showTitleBar)
                .setProgressBarVisible(showProgressBar)
                .setReceiveTitle(receiveTitleFlag)
                .setTitle(title)
                .setUrl(url)
                .build()

    }

}