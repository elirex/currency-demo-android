package com.elirex.currencydemo.domain

import com.elirex.currencydemo.CoroutineRule
import com.elirex.currencydemo.data.mock.MockCurrencyRepository
import com.elirex.currencydemo.data.mock.MockData.mockCurrencyInfoList
import com.elirex.currencydemo.domain.currency.LoadCurrencyInfoUseCase
import com.elirex.currencydemo.result.Result
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoadCurrencyInfoUseCaseTest {

    @get:Rule
    val coroutineRule = CoroutineRule()

    private lateinit var mockCurrencyRepository: MockCurrencyRepository
    private lateinit var useCase: LoadCurrencyInfoUseCase

    @Before
    fun setup() {
        mockCurrencyRepository = MockCurrencyRepository()
        useCase = LoadCurrencyInfoUseCase(mockCurrencyRepository, coroutineRule.dispatcher)
    }

    @Test
    fun `test success`() = runBlockingTest {
        val result = useCase.invoke(Unit)
        Assert.assertTrue(result is Result.Success)
        Assert.assertEquals((result as Result.Success).data, mockCurrencyInfoList)
    }

    @Test
    fun `test failure`() = runBlockingTest {
        mockCurrencyRepository.isFailed = true
        val result = useCase.invoke(Unit)
        Assert.assertTrue(result is Result.Error)
    }

}