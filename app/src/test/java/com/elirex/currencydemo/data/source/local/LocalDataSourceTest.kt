package com.elirex.currencydemo.data.source.local

import com.elirex.currencydemo.data.mock.MockData.mockCurrencyEntities
import com.elirex.currencydemo.data.source.local.db.CurrencyDao
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LocalDataSourceTest {

    private lateinit var dataSource: LocalDataSource

    @Before
    fun setup() {
        val dao: CurrencyDao = mockk {
            coEvery { queryAllCurrencies() } returns mockCurrencyEntities
            coEvery { queryAllCurrenciesByAscending() } returns mockCurrencyEntities.sortedBy { it.currencyId }
        }
        dataSource = LocalDataSource(dao)
    }

    @Test
    fun `test get all currencies`() = runBlockingTest {
        val list = dataSource.getAllCurrencies()
        Assert.assertTrue(list.size == 3)
        Assert.assertEquals(list, mockCurrencyEntities)
    }

}