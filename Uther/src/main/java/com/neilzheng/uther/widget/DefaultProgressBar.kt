package com.neilzheng.uther.widget

import android.annotation.TargetApi
import android.content.Context
import android.content.res.ColorStateList
import android.os.Build
import android.util.AttributeSet
import android.widget.ProgressBar
import com.neilzheng.uther.R
import com.neilzheng.uther.utils.dp2px
import com.neilzheng.uther.utils.dp2pxf

/**
 * Created by Neil Zheng on 2017/7/3.
 */

open class DefaultProgressBar : ProgressBar {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        elevation = dp2pxf(context, 2f)
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        progressTintList = ColorStateList.valueOf(context.resources.getColor(R.color.colorAccent))
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(dp2px(context, progressHeight()),
                MeasureSpec.EXACTLY))
    }

    open fun progressHeight(): Float {
        return 2f
    }
}
