package com.elirex.currencydemo.ui.util

import android.os.SystemClock
import android.view.View

class DebounceViewClickListener(
    private val timeMillis: Long = DEFAULT_TIME_MILLIS,
    private val onDebounceClick: (View) -> Unit
) : View.OnClickListener {

    companion object {
        private const val DEFAULT_TIME_MILLIS = 600L
    }

    private var lastTimeMillis = 0L

    override fun onClick(v: View) {
        val currentTimeMillis = SystemClock.elapsedRealtime()
        if (currentTimeMillis - lastTimeMillis < timeMillis) {
            return
        }
        lastTimeMillis = currentTimeMillis
        onDebounceClick(v)
    }
}