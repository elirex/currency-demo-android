package com.elirex.currencydemo.data.mock

import com.elirex.currencydemo.data.source.local.db.CurrencyEntity

object MockData {

    val mockCurrencyEntities: List<CurrencyEntity> by lazy {
        listOf(
            CurrencyEntity("USD", "United States Dollar"),
            CurrencyEntity("EUR", "Euro"),
            CurrencyEntity("BTC", "Bitcoin"),
        )
    }
}