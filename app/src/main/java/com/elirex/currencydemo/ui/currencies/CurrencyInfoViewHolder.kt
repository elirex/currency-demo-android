package com.elirex.currencydemo.ui.currencies

import androidx.recyclerview.widget.RecyclerView
import com.elirex.currencydemo.databinding.ItemCurrencyInfoBinding
import com.elirex.currencydemo.model.CurrencyInfo

class CurrencyInfoViewHolder(private val binding: ItemCurrencyInfoBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(currencyInfo: CurrencyInfo) {
        binding.currencyInfo = currencyInfo
        binding.executePendingBindings()
    }
}