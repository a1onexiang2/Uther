package com.neilzheng.uther.sample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.neilzheng.uther.UtherParams
import kotlinx.android.synthetic.main.activity_webview_test_webview.*

/**
 * Created by Neil Zheng on 2017/6/26.
 */

class TestWebViewActivity : AppCompatActivity() {

    private val list = arrayOf("http://www.vip.com",
            "https://h5.m.jd.com/active/download/download.html?channel=jd-msy1",
            "file:///android_asset/upload_file/uploadfile.html",
            "file:///android_asset/upload_file/jsuploadfile.html",
            "file:///android_asset/js_interaction/hello.html",
            "http://broken-links.com/tests/video/",
            "https://m.bilibili.com/video/av11484069.html",
            "http://www.taobao.com",
            "http://www.wandoujia.com/apps",
            "file:///android_asset/sms/sms.html",
            "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview_test_webview)
        initView()
    }

    private fun initView() {
        recyclerView.layoutManager = GridLayoutManager(this@TestWebViewActivity, 2)
        val adapter = MySimpleRecyclerAdapter(list)
        recyclerView.adapter = adapter
        adapter.setOnClickListener(object : BaseViewHolder.onItemClickListener {
            override fun onItemClick(position: Int) {
                doItemClick(list[position])
            }
        })
        radio_5.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                startActivity(Intent(this@TestWebViewActivity, SimpleWebViewViewPagerActivity::class.java))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if(radio_5.isChecked) {
            radio_5.isChecked = false
            radio_1.isChecked = true
        }
    }

    private fun doItemClick(url: String) {
        val intent: Intent = when (radioGroup.checkedRadioButtonId) {
            R.id.radio_1 -> {
                Intent(this@TestWebViewActivity, SimpleWebViewActivity::class.java)
            }
            R.id.radio_2 -> {
                intent = Intent(this@TestWebViewActivity, SimpleWebViewActivity::class.java)
                intent.putExtra(UtherParams.EXTRA_RECEIVE_TITLE, false)
                intent.putExtra(UtherParams.EXTRA_TITLE, "我十动然拒了所有网页标题")
                intent
            }
            R.id.radio_3 -> {
                Intent(this@TestWebViewActivity, SizedWebViewActivity::class.java)
            }
            R.id.radio_4 -> {
                Intent(this@TestWebViewActivity, SimpleWebViewFragmentActivity::class.java)
            }
            else -> Intent(this@TestWebViewActivity, SimpleWebViewActivity::class.java)
        }
        intent.putExtra(UtherParams.EXTRA_URL, url)
        startActivity(intent)
    }
}