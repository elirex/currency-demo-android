package com.elirex.currencydemo.ui.currencies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.elirex.currencydemo.databinding.ItemCurrencyInfoBinding
import com.elirex.currencydemo.model.CurrencyInfo
import com.elirex.currencydemo.ui.extension.setOnDebounceClickListener

class CurrencyInfoListAdapter(private val actionListener: ActionListener) : ListAdapter<CurrencyInfo, CurrencyInfoViewHolder>(ItemDiffUtil()) {

    class ItemDiffUtil : DiffUtil.ItemCallback<CurrencyInfo>() {
        override fun areItemsTheSame(oldItem: CurrencyInfo, newItem: CurrencyInfo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CurrencyInfo, newItem: CurrencyInfo): Boolean {
            return oldItem == newItem
        }
    }

    interface ActionListener {
        fun onItemClicked(currencyInfo: CurrencyInfo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyInfoViewHolder {
        return CurrencyInfoViewHolder(ItemCurrencyInfoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: CurrencyInfoViewHolder, position: Int) {
        with(holder) {
            val currencyInfo = getItem(position)
            bind(currencyInfo)
            itemView.setOnDebounceClickListener {
                actionListener.onItemClicked(currencyInfo)
            }
        }
    }
}