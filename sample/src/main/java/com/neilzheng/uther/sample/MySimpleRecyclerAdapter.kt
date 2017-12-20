package com.neilzheng.uther.sample

import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

/**
 * Created by Neil Zheng on 2017/6/26.
 */
class MySimpleRecyclerAdapter(val list: Array<String>) : RecyclerView.Adapter<BaseViewHolder>() {

    private var onClickListener: BaseViewHolder.onItemClickListener? = null
    private var onLongClickListener: BaseViewHolder.onItemLongClickListener? = null

    fun setOnClickListener(listener: BaseViewHolder.onItemClickListener) {
        onClickListener = listener
    }

    fun setOnLongClickListener(listener: BaseViewHolder.onItemLongClickListener) {
        onLongClickListener = listener
    }

    override fun onCreateViewHolder(vg: ViewGroup?, position: Int): BaseViewHolder {
        return MyViewHolder(View.inflate(vg?.context, R.layout.item_webview_simple_recyclerview, null));
    }

    override fun onBindViewHolder(holder: BaseViewHolder?, position: Int) {
        if (holder is MyViewHolder) {
            holder.button.text = list[position]
            if (null != onClickListener) {
                holder.itemView.setOnClickListener { onClickListener!!.onItemClick(position) }
            }
            if (null != onLongClickListener) {
                holder.itemView.setOnLongClickListener { onLongClickListener!!.onItemLongClick(position) }
            }
        }
    }

    fun getItem(position: Int): String {
        return list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MyViewHolder(view: View) : BaseViewHolder(view) {
        var button: AppCompatButton = view.findViewById<AppCompatButton>(R.id.button)
    }

}