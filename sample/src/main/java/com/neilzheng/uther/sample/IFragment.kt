package com.neilzheng.uther.sample

import android.support.annotation.LayoutRes

/**
 * Created by Neil Zheng on 2017/7/5.
 */
interface IFragment {

    @get:LayoutRes
    val layoutId: Int

    fun initView()

    fun initIntentData()

    fun initData()

    fun initListener()

    fun onResumeView()

    fun onPauseView()

}