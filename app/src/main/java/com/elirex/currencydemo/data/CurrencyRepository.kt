package com.elirex.currencydemo.data

import com.elirex.currencydemo.coroutine.JobController
import com.elirex.currencydemo.data.source.DataSource
import com.elirex.currencydemo.model.CurrencyInfo
import javax.inject.Inject

interface CurrencyRepository {
    suspend fun getAllCurrencies(): List<CurrencyInfo>
}

class CurrencyRepositoryImpl @Inject constructor(
    private val dataSource: DataSource
) : CurrencyRepository {

    private val jobController = JobController<List<CurrencyInfo>>()

    override suspend fun getAllCurrencies(): List<CurrencyInfo> {
        return jobController.joinPreviousOrRun {
            mutableListOf<CurrencyInfo>().apply {
                dataSource.getAllCurrencies().forEach {
                    add(CurrencyInfo(it.currencyId, it.name))
                }
            }
        }
    }

}