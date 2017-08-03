package com.neilzheng.uther.handler

/**
 * Created by Neil Zheng on 2017/6/19.
 */


interface ILifecycle {

    fun onResume()
    fun onPause()
    fun onDestroy()

}