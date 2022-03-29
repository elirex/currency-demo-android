package com.elirex.currencydemo.domain

import com.elirex.currencydemo.CoroutineRule
import com.elirex.currencydemo.data.mock.MockData.mockCurrencyInfoList
import com.elirex.currencydemo.domain.currency.SortCurrencyInfoUseCase
import com.elirex.currencydemo.result.Result
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SortCurrencyInfoUseCaseTest {

    @get:Rule
    val coroutineRule = CoroutineRule()

    private lateinit var useCase: SortCurrencyInfoUseCase

    @Before
    fun setup() {
        useCase = SortCurrencyInfoUseCase(coroutineRule.dispatcher)
    }

    @Test
    fun `test sort`() = runBlockingTest {
        val result = useCase.invoke(mockCurrencyInfoList)
        Assert.assertTrue(result is Result.Success)
        val actualList = (result as Result.Success).data
        mockCurrencyInfoList.sortedBy { it.id }.forEachIndexed { index, currencyInfo ->
            Assert.assertEquals(currencyInfo, actualList[index])
        }
    }

}