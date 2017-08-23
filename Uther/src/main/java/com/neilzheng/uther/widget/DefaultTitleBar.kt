package com.neilzheng.uther.widget;

import android.app.Activity
import android.content.Context
import android.os.Build
import android.support.v4.view.ViewCompat
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import com.neilzheng.uther.R
import com.neilzheng.uther.utils.dp2px
import com.neilzheng.uther.utils.dp2pxf

/**
 * Created by Neil Zheng on 2017/7/3.
 */

class DefaultTitleBar : Toolbar {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            elevation = dp2pxf(context, 2f)
        } else {
            ViewCompat.setElevation(this, dp2pxf(context, 2f))
        }
        setBackgroundColor(context.resources.getColor(R.color.colorPrimary))
        setNavigationIcon(R.drawable.abc_ic_ab_back_material)
        setNavigationOnClickListener { v ->
            if (context is Activity) {
                (context as Activity).finish()
            }
        }
    }
}
