package com.exercises.eventmanagement.presentation.presenters
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exercises.eventmanagement.data.database.entities.PayrollEntity
import com.exercises.eventmanagement.data.database.repositories.LocalRepository
import com.exercises.eventmanagement.data.mapper.toModel
import com.exercises.eventmanagement.presentation.domain.EventModel
import com.exercises.eventmanagement.presentation.domain.PersonModel
import com.exercises.eventmanagement.presentation.usecase.EventInfoUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import com.exercises.eventmanagement.presentation.usecase.PersonInfoUseCase
import com.exercises.eventmanagement.presentation.usecase.PayrollInfoUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalCoroutinesApi::class)
class PayrollAddPageViewModel: ViewModel(), KoinComponent {
    private val localRepository: LocalRepository by inject()

    private val personInfoUseCase: PersonInfoUseCase by inject()
    private val eventInfoUseCase: EventInfoUseCase by inject()

    private val _events = MutableStateFlow<List<EventModel>>(emptyList())
    private val _persons = MutableStateFlow<List<PersonModel>>(emptyList())

    private val refreshing = MutableStateFlow(false)

    private val _uiState = MutableStateFlow<AddPayrollScreenUiState>(AddPayrollScreenUiState.Loading)
    val uiState: StateFlow<AddPayrollScreenUiState>
        get() = _uiState

    init {
        viewModelScope.launch {
            combine(
                _events.flatMapLatest { eventInfoUseCase() },
                _persons.flatMapLatest { personInfoUseCase() },
                refreshing
            ) { events, persons, refreshing ->

                val uiState = if (refreshing){
                    Log.d(TAG, "refreshing: $refreshing")
                    AddPayrollScreenUiState.Loading
                } else {
                    AddPayrollScreenUiState.Ready(
                        events = events,
                        persons = persons
                    )
                }
                uiState

            }.catch { throwable ->
                Log.d(TAG, "catch: ${throwable.message}")
                AddPayrollScreenUiState.Error(throwable.message)
            }.collect {
                _uiState.value = it
            }
            }
        }


    fun savePayroll(payroll: PayrollEntity) {
        viewModelScope.launch {
            localRepository.insertPayroll(payroll)
        }
    }

    companion object {
        private const val TAG = "PayrollAddPageViewModel"
    }
}


sealed interface AddPayrollScreenUiState {
    data object  Loading: AddPayrollScreenUiState

    data class Error(
        val errorMassage: String? = null
    ) : AddPayrollScreenUiState

    data class Ready (
        val events: List<EventModel> = emptyList(),
        val persons: List<PersonModel> = emptyList()
    ) : AddPayrollScreenUiState
}