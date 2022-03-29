package com.elirex.currencydemo.ui

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.elirex.currencydemo.model.CurrencyInfo
import com.elirex.currencydemo.ui.currencies.CurrencyInfoListAdapter

object UiBindings {

    @JvmStatic
    @BindingAdapter("listData")
    fun setListData(recyclerView: RecyclerView, list: List<CurrencyInfo>) {
        (recyclerView.adapter as? CurrencyInfoListAdapter)?.submitList(list)
        recyclerView.postDelayed({
            recyclerView.smoothScrollToPosition(0)
        }, 300)
    }

}