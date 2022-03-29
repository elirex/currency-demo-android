package com.elirex.currencydemo.data.source

import com.elirex.currencydemo.data.source.local.db.CurrencyEntity

interface DataSource {
    suspend fun getAllCurrencies(): List<CurrencyEntity>
}