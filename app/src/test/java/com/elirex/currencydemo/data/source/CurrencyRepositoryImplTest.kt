package com.elirex.currencydemo.data.source

import com.elirex.currencydemo.data.CurrencyRepository
import com.elirex.currencydemo.data.CurrencyRepositoryImpl
import com.elirex.currencydemo.data.mock.MockData.mockCurrencyEntities
import com.elirex.currencydemo.data.source.local.db.CurrencyEntity
import com.elirex.currencydemo.model.CurrencyInfo
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CurrencyRepositoryImplTest {

    private lateinit var mockDataSource: MockDataSource
    private lateinit var repository: CurrencyRepository

    @Before
    fun setup() {
        mockDataSource = MockDataSource()
        repository = CurrencyRepositoryImpl(mockDataSource)
    }

    @Test
    fun `test getAllCurrencies`() = runBlockingTest {
        val list = repository.getAllCurrencies()
        Assert.assertTrue(list.size == 3)

        val answerList = mutableListOf<CurrencyInfo>().apply {
            mockCurrencyEntities.forEach {
                add(CurrencyInfo(it.currencyId, it.name))
            }
        }
        list.forEach { element ->
            Assert.assertTrue(answerList.find { element.id == it.id } != null)
        }
    }

    @Test
    fun `test getAllCurrencies for failure`() = runBlockingTest {
        val mockException = Exception("Failed to get all currencies.").also {
            mockDataSource.exception = it
        }
        try {
            repository.getAllCurrencies()
        } catch (e: Exception) {
            Assert.assertEquals(e.message, mockException.message)
        }
    }

    class MockDataSource(var exception: Exception? = null) : DataSource {
        override suspend fun getAllCurrencies(): List<CurrencyEntity> {
            if (exception != null) {
                throw exception as Exception
            } else {
                return mockCurrencyEntities
            }
        }
    }


}