package com.exercises.eventmanagement.presentation.presenters

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exercises.eventmanagement.data.database.entities.relations.PayrollWithPersonSalary
import com.exercises.eventmanagement.presentation.domain.EventModel
import com.exercises.eventmanagement.presentation.domain.PersonModel
import com.exercises.eventmanagement.presentation.usecase.EventInfoUseCase
import com.exercises.eventmanagement.presentation.usecase.PayrollInfoUseCase
import com.exercises.eventmanagement.presentation.usecase.PersonInfoUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


@OptIn(ExperimentalCoroutinesApi::class)
class PayrollPageViewModel : ViewModel(), KoinComponent {
    private val payrollInfoUseCase: PayrollInfoUseCase by inject()
    private val personInfoUseCase: PersonInfoUseCase by inject()
    private val eventInfoUseCase: EventInfoUseCase by inject()

    //listas de personas y eventos
    private val _payrolls = MutableStateFlow<List<PayrollWithPersonSalary>>(emptyList())
    private val _events = MutableStateFlow<List<EventModel>>(emptyList())
    private val _persons = MutableStateFlow<List<PersonModel>>(emptyList())

    private val refreshing = MutableStateFlow(false)

    private val _uiState = MutableStateFlow<PayrollScreenUiState>(PayrollScreenUiState.Loading)
    val uiState: StateFlow<PayrollScreenUiState>
        get() = _uiState

    var errorShowed: Boolean = false

    init {
        viewModelScope.launch {
            combine(
                _payrolls.flatMapLatest { payrollInfoUseCase() },
                _events.flatMapLatest { eventInfoUseCase() },
                _persons.flatMapLatest { personInfoUseCase() },
                refreshing
            ) { payrolls, events, persons, refreshing ->

                val uiState = if (refreshing) {
                    Log.d(TAG, "refreshing: $refreshing")
                    PayrollScreenUiState.Loading
                } else {
                    PayrollScreenUiState.Ready(
                        payrolls = payrolls,
                        events = events,
                        persons = persons
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
        val payrolls: List<PayrollWithPersonSalary> = emptyList(),
        val events: List<EventModel> = emptyList(),
        val persons: List<PersonModel> = emptyList()
    ) : PayrollScreenUiState
}