package com.github.weiggle.jetpack.utils

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewTreeLifecycleOwner
import kotlinx.coroutines.*

/**
 * @author wei.li
 * @created on 2022/5/26
 * @desc desc
 *
 */
class IntervalTask<T>(val lifecycleOwner: LifecycleOwner) : DefaultLifecycleObserver {

    var scope: CoroutineScope? = null
    var count = 0
    var mInterval = 0L
    var mCallback: (() -> Unit)? = null

    init {
        lifecycleOwner.lifecycle.addObserver(this)
    }


    fun interval(interval: Long, result: () -> Unit) {
        mInterval = interval
        mCallback = result
        if (mInterval <= 0) return
        scope = CoroutineScope(Dispatchers.IO)
        scope?.launch {
            while (isActive) {
                count++
                result.invoke()
                println("this is interval ${count}")
                delay(interval)
            }
        }
    }


    fun cancel() {
        scope?.cancel()
        scope = null
    }


    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        cancel()
        mCallback?.let {
            interval(mInterval, it)
        }
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        cancel()
    }

}