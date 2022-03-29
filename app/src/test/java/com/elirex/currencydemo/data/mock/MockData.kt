package com.elirex.currencydemo.data.mock

import com.elirex.currencydemo.data.source.local.db.CurrencyEntity
import com.elirex.currencydemo.model.CurrencyInfo

object MockData {

    val mockCurrencyEntities: List<CurrencyEntity> by lazy {
        listOf(
            CurrencyEntity("USD", "United States Dollar"),
            CurrencyEntity("EUR", "Euro"),
            CurrencyEntity("BTC", "Bitcoin"),
        )
    }

    val mockCurrencyInfoList: List<CurrencyInfo> by lazy {
        listOf(
            CurrencyInfo("USD", "United States Dollar"),
            CurrencyInfo("EUR", "Euro"),
            CurrencyInfo("BTC", "Bitcoin"),
        )
    }
}