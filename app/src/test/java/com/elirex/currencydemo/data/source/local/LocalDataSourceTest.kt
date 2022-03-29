package com.elirex.currencydemo.data.source.local

import com.elirex.currencydemo.data.source.local.db.CurrencyDao
import com.elirex.currencydemo.data.source.local.db.CurrencyEntity
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
        val list = dataSource.getAllCurrencies(false)
        Assert.assertTrue(list.size == 3)
        Assert.assertEquals(list, mockCurrencyEntities)
    }

    @Test
    fun `test get all sorted currencies `() = runBlockingTest {
        val list = dataSource.getAllCurrencies(true)
        Assert.assertTrue(list.size == 3)
        Assert.assertEquals(list, mockCurrencyEntities.sortedBy { it.currencyId })
    }


    companion object {
        val mockCurrencyEntities: List<CurrencyEntity> by lazy {
            listOf(
                CurrencyEntity("USD", "United States Dollar"),
                CurrencyEntity("EUR", "Euro"),
                CurrencyEntity("BTC", "Bitcoin"),
            )
        }
    }

}