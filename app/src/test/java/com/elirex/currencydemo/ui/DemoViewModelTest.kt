package com.elirex.currencydemo.ui

import android.view.inputmethod.CorrectionInfo
import com.elirex.currencydemo.CoroutineRule
import com.elirex.currencydemo.R
import com.elirex.currencydemo.data.mock.MockCurrencyRepository
import com.elirex.currencydemo.data.mock.MockData.mockCurrencyInfoList
import com.elirex.currencydemo.domain.currency.LoadCurrencyInfoUseCase
import com.elirex.currencydemo.domain.currency.SortCurrencyInfoUseCase
import com.elirex.currencydemo.result.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Exception

class DemoViewModelTest {

    @get:Rule
    val coroutineRule = CoroutineRule()

    private lateinit var mockCurrencyRepository: MockCurrencyRepository
    private lateinit var mockLoadCurrencyInfoUseCase: LoadCurrencyInfoUseCase
    private lateinit var mockSortCurrencyInfoUseCase: SortCurrencyInfoUseCase


    private lateinit var viewModel: DemoViewModel

    @Before
    fun setup() {
        mockCurrencyRepository = MockCurrencyRepository()
        mockLoadCurrencyInfoUseCase = LoadCurrencyInfoUseCase(mockCurrencyRepository, coroutineRule.dispatcher)
        mockSortCurrencyInfoUseCase = mockk()
        viewModel = DemoViewModel(mockLoadCurrencyInfoUseCase, mockSortCurrencyInfoUseCase)
    }

    @Test
    fun `test load success`() = runBlockingTest {
        viewModel.loadCurrencyInfoList()
        Assert.assertEquals(viewModel.currencyInfoList.value, mockCurrencyInfoList)
    }

    @Test
    fun `test load failure`() = runBlockingTest {
        mockCurrencyRepository.isFailed = true
        viewModel.loadCurrencyInfoList()
        Assert.assertTrue(viewModel.currencyInfoList.value.isEmpty())
    }

    @Test
    fun `test sort success`() = runBlockingTest {
        viewModel.loadCurrencyInfoList()
        coEvery { mockSortCurrencyInfoUseCase.invoke(mockCurrencyInfoList)  } returns Result.Success(mockCurrencyInfoList.sortedBy { it.id })
        viewModel.sortCurrencyInfoList()
        val actualList = viewModel.currencyInfoList.value
        mockCurrencyInfoList.sortedBy { it.id }.forEachIndexed { index, currencyInfo ->
            Assert.assertEquals(currencyInfo, actualList[index])
        }
    }

    @Test
    fun `test sort failure`() = runBlockingTest {
        val mockResultError = Result.Error(Exception("Mock error"))
        coEvery { mockSortCurrencyInfoUseCase.invoke(mockCurrencyInfoList) } returns mockResultError
        viewModel.sortCurrencyInfoList()
        Assert.assertTrue(viewModel.currencyInfoList.value.isEmpty())
    }

}