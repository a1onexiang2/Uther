package com.neilzheng.uther.sample

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.ViewGroup
import com.neilzheng.uther.Uther
import com.neilzheng.uther.utils.dp2px
import kotlinx.android.synthetic.main.activity_webview_test_2.*

/**
 * Created by Neil Zheng on 2017/6/15.
 */

class SizedWebViewActivity : BaseWebViewActivity(), TextWatcher {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview_test_2)
        initView()
        initWebView()
    }

    private fun initView() {
        width.addTextChangedListener(this@SizedWebViewActivity)
        height.addTextChangedListener(this@SizedWebViewActivity)
    }

    override fun afterTextChanged(s: Editable?) {
        val width = getInputWidth()
        val height = getInputHeight()
        if (width * height > 0) {
            uther.setSize(width, height)
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    private fun getInputWidth(): Int {
        val result = width.text.toString().toInt()
        return if (result == 0) ViewGroup.LayoutParams.MATCH_PARENT else dp2px(this@SizedWebViewActivity, result.toFloat())
    }

    private fun getInputHeight(): Int {
        val result = height.text.toString().toInt()
        return if (result == 0) ViewGroup.LayoutParams.MATCH_PARENT else dp2px(this@SizedWebViewActivity, result.toFloat())
    }

    private fun initWebView() {
        uther = Uther.with(frame)
                .setTitleBarVisible(showTitleBar)
                .setProgressBarVisible(showProgressBar)
                .setReceiveTitle(receiveTitleFlag)
                .setTitle(title)
                .setUrl(url)
                .build()
    }

}