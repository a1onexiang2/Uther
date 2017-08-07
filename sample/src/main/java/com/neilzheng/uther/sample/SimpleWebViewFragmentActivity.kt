package com.neilzheng.uther.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.neilzheng.uther.UtherParams

/**
 * Created by Neil Zheng on 2017/7/4.
 */

class SimpleWebViewFragmentActivity: AppCompatActivity() {

    private var url: String? = null
    private var title: String? = null
    private var receiveTitleFlag = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview_test_webview_fragment)
        initIntentData()
        initData()
    }

    private fun initIntentData() {
        if (intent.hasExtra(UtherParams.EXTRA_URL)) {
            url = intent.getStringExtra(UtherParams.EXTRA_URL)
        }
        if(intent.hasExtra(UtherParams.EXTRA_TITLE)) {
            title = intent.getStringExtra(UtherParams.EXTRA_TITLE)
        }
        receiveTitleFlag = intent.getBooleanExtra(UtherParams.EXTRA_RECEIVE_TITLE, true)
    }

    private fun initData() {
        val fragment = SimpleWebViewFragment()
        val arguments = Bundle()
        arguments.putString(UtherParams.EXTRA_URL, url)
        arguments.putString(UtherParams.EXTRA_TITLE, title)
        arguments.putBoolean(UtherParams.EXTRA_RECEIVE_TITLE, receiveTitleFlag)
        fragment.arguments = arguments
        val transaction = supportFragmentManager.beginTransaction();
        transaction.add(R.id.frame, fragment)
        transaction.commit()
    }

}