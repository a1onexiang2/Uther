package com.neilzheng.uther.sample;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.ViewGroup;

import com.neilzheng.uther.Uther;

/**
 * Created by Neil Zheng on 2017/8/7.
 */

public class SimpleWebViewActivityJava extends BaseWebViewActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayoutCompat root = new LinearLayoutCompat(this);
        root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        setContentView(root);
        initWebView();
    }

    private void initWebView() {
        uther = Uther.with(this)
                .setTitleBarVisible(getShowTitleBar())
                .setProgressBarVisible(getShowProgressBar())
                .setReceiveTitle(getReceiveTitleFlag())
                .setTitle(getTitle())
                .setUrl(getUrl())
                .build();
    }
}
