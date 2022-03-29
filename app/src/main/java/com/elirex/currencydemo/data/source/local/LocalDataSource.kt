package com.elirex.currencydemo.data.source.local

import com.elirex.currencydemo.data.source.DataSource
import com.elirex.currencydemo.data.source.local.db.CurrencyDao
import com.elirex.currencydemo.data.source.local.db.CurrencyEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dao: CurrencyDao,
): DataSource {

    override suspend fun getAllCurrencies(): List<CurrencyEntity> = dao.queryAllCurrencies()

}