package com.neilzheng.uther.sample

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import com.neilzheng.uther.UtherParams
import kotlinx.android.synthetic.main.activity_webview_test_viewpager.*

/**
 * Created by Neil Zheng on 2017/7/4.
 */

class SimpleWebViewViewPagerActivity : AppCompatActivity() {

    private val list = arrayOf("http://www.vip.com",
            "https://h5.m.jd.com/active/download/download.html?channel=jd-msy1",
            "http://broken-links.com/tests/video/",
            "https://m.bilibili.com/video/av11484069.html",
            "http://www.taobao.com",
            "file:///android_asset/sms/sms.html",
            "")
    private val fragments = arrayListOf<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview_test_viewpager)
        initView()
    }

    private fun initView() {
        viewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment = getFragmentItem(position)
            override fun getCount(): Int = list.size
            override fun getPageTitle(position: Int): CharSequence = position.toString()
        }
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun getFragmentItem(position: Int): Fragment {
        if (fragments.isEmpty()) {
            initFragments()
        }
        return fragments[position]
    }

    private fun initFragments() {
        for ((index, string) in list.withIndex()) {
            val fragment = SimpleWebViewFragment()
            val arguments = Bundle()
            arguments.putString(UtherParams.EXTRA_URL, string)
            arguments.putString(UtherParams.EXTRA_TITLE, index.toString())
            arguments.putBoolean(UtherParams.EXTRA_RECEIVE_TITLE, index % 2 == 0)
            arguments.putBoolean(UtherParams.EXTRA_SHOW_TITLEBAR, true)
            arguments.putBoolean(UtherParams.EXTRA_SHOW_PROGRESSBAR, true)
            fragment.arguments = arguments
            fragments.add(fragment)
        }
    }
}
