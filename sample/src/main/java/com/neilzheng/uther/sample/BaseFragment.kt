package com.neilzheng.uther.sample

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment : Fragment(), IFragment {

    private var rootView: View? = null
    private var initialized = false
    private var isVisibleToUser = false
    private var isInViewPager = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (null == rootView) {
            rootView = inflater.inflate(layoutId, container, false)
            // 解决点击穿透问题
            rootView?.setOnTouchListener { _, _ -> true }
        }
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initIntentData()
        if ((!isInViewPager || isVisibleToUser) && !initialized) {
            initData()
            initialized = true
        }
        initListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (null != rootView) {
            (rootView!!.parent as ViewGroup).removeView(rootView)
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        this.isVisibleToUser = isVisibleToUser
        isInViewPager = true
        if (isAdded) {
            if (isVisibleToUser) {
                onResumeView()
            } else {
                onPauseView()
            }
        }
    }

    override fun initView() {}

    override fun initListener() {}

    override fun initIntentData() {}

    override fun onResumeView() {
        if (!initialized) {
            initData()
            initialized = true
        }
    }

    override fun onPauseView() {}
}