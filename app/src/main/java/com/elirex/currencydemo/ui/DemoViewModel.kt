package com.elirex.currencydemo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elirex.currencydemo.R
import com.elirex.currencydemo.domain.currency.LoadCurrencyInfoUseCase
import com.elirex.currencydemo.domain.currency.SortCurrencyInfoUseCase
import com.elirex.currencydemo.model.CurrencyInfo
import com.elirex.currencydemo.result.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DemoViewModel @Inject constructor(
    private val loadUserCase: LoadCurrencyInfoUseCase,
    private val sortUseCase: SortCurrencyInfoUseCase
): ViewModel() {

    private val _currencyInfoList = MutableStateFlow<List<CurrencyInfo>>(emptyList())
    val currencyInfoList: StateFlow<List<CurrencyInfo>> = _currencyInfoList

    private val _showSnackbar = MutableSharedFlow<Int>(0)
    val showSnackbar: SharedFlow<Int> = _showSnackbar

    private val _isProcessing = MutableStateFlow<Boolean>(false)
    val isProcessing: StateFlow<Boolean> = _isProcessing


    fun loadCurrencyInfoList() {
        viewModelScope.launch {
            _isProcessing.value = true
            _showSnackbar.emit(R.string.processing)
            when (val result = loadUserCase.invoke(Unit)) {
                is Result.Success -> _currencyInfoList.value = result.data
                is Result.Error -> _showSnackbar.emit(R.string.error_load_data)
                else -> {
                    // Do nothing....
                }
            }
            _isProcessing.value = false
        }
    }

    fun sortCurrencyInfoList() {
        viewModelScope.launch {
            val list = currencyInfoList.value
            if (list.isEmpty()) {
                _showSnackbar.emit(R.string.error_empty_list_cannot_sort)
            } else {
                _isProcessing.value = true
                _showSnackbar.emit(R.string.processing)
                when (val result = sortUseCase.invoke(list)) {
                    is Result.Success -> _currencyInfoList.value = result.data
                    is Result.Error -> _showSnackbar.emit(R.string.error_sort_data)
                    else -> {
                        // Do nothing...
                    }
                }
                _isProcessing.value = false
            }
        }
    }

}