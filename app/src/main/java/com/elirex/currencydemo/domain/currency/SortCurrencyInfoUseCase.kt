package com.elirex.currencydemo.domain.currency

import com.elirex.currencydemo.coroutine.JobController
import com.elirex.currencydemo.di.DefaultDispatcher
import com.elirex.currencydemo.domain.UseCase
import com.elirex.currencydemo.model.CurrencyInfo
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SortCurrencyInfoUseCase @Inject constructor(
    @DefaultDispatcher dispatcher: CoroutineDispatcher
) : UseCase<List<CurrencyInfo>, List<CurrencyInfo>>(dispatcher) {

    private val jobController = JobController<List<CurrencyInfo>>()

    override suspend fun execute(parameters: List<CurrencyInfo>): List<CurrencyInfo> {
        return jobController.cancelPreviousThenRun {
            parameters.sortedBy { it.id }
        }
    }
}