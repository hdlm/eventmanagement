package com.exercises.eventmanagment.presentation.presenters

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exercises.eventmanagment.presentation.domain.PayrollModel
import com.exercises.eventmanagment.presentation.usecase.PayrollInfoUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


@OptIn(ExperimentalCoroutinesApi::class)
class PayrollPageViewModel : ViewModel(), KoinComponent {
    private val payrollInfoUseCase: PayrollInfoUseCase by inject()

    private val _payrolls = MutableStateFlow<List<PayrollModel>>(emptyList())

    private val refreshing = MutableStateFlow(false)

    private val _uiState = MutableStateFlow<PayrollScreenUiState>(PayrollScreenUiState.Loading)
    val uiState: StateFlow<PayrollScreenUiState>
        get() = _uiState

    var errorShowed: Boolean = false

    init {
        viewModelScope.launch {
            com.exercises.eventmanagment.commons.combine(
                _payrolls.flatMapLatest {
                    payrollInfoUseCase()
                },
                refreshing
            ) { payrolls,
                refreshing ->

                val uiState = if (refreshing) {
                    Log.d(TAG, "refreshing: $refreshing")
                    PayrollScreenUiState.Loading
                } else {
                    PayrollScreenUiState.Ready(
                        payrolls = payrolls
                    )
                }
                uiState

            }.catch { throwable ->
                throwable.printStackTrace()
                _uiState.value = PayrollScreenUiState.Error(throwable.message)
                Log.d(TAG, "error: ${throwable.message}")
            }.collect {
                _uiState.value = it
            }
        }
    }
}
private const val TAG = "PayrollPageViewModel"

sealed interface PayrollScreenUiState {
    data object  Loading: PayrollScreenUiState

    data class Error(
        val errorMassage: String? = null
    ) : PayrollScreenUiState

    data class Ready (
        val payrolls: List<PayrollModel> = emptyList()
    ) : PayrollScreenUiState
}