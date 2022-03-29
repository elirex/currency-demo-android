package com.elirex.currencydemo.ui.extension

import android.view.View
import com.elirex.currencydemo.ui.util.DebounceViewClickListener

inline fun View.setOnDebounceClickListener(crossinline onDebounceClick: (View) -> Unit) {
    setOnClickListener(DebounceViewClickListener() {
        onDebounceClick(it)
    })
}