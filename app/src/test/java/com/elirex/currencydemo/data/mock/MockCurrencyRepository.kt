package com.elirex.currencydemo.data.mock

import com.elirex.currencydemo.data.CurrencyRepository
import com.elirex.currencydemo.model.CurrencyInfo
import java.lang.Exception

class MockCurrencyRepository(var isFailed: Boolean = false) : CurrencyRepository {
    override suspend fun getAllCurrencies(): List<CurrencyInfo> {
        if (isFailed) {
            throw Exception("Failed to get currencies")
        } else {
            return MockData.mockCurrencyInfoList
        }
    }

}
