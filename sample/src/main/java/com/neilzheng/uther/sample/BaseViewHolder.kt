package com.neilzheng.uther.sample

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by Neil Zheng on 2017/7/4.
 */


open class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    interface onItemLongClickListener {
        fun onItemLongClick(position: Int): Boolean
    }

}