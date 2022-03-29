package com.elirex.currencydemo.domain.currency

import com.elirex.currencydemo.data.CurrencyRepository
import com.elirex.currencydemo.di.IoDispatcher
import com.elirex.currencydemo.domain.UseCase
import com.elirex.currencydemo.model.CurrencyInfo
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class LoadCurrencyInfoUseCase @Inject constructor(
    private val repository: CurrencyRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<Unit, List<CurrencyInfo>>(dispatcher) {

    override suspend fun execute(parameters: Unit): List<CurrencyInfo> {
        return repository.getAllCurrencies()
    }
}