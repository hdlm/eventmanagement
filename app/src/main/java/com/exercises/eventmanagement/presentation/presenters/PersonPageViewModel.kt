package com.exercises.eventmanagment.presentation.presenters

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exercises.eventmanagment.presentation.domain.PersonModel
import com.exercises.eventmanagment.presentation.usecase.PersonInfoUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


@OptIn(ExperimentalCoroutinesApi::class)
class PersonPageViewModel : ViewModel(), KoinComponent {
    private val personInfoUseCase: PersonInfoUseCase by inject()

    private val _persons = MutableStateFlow<List<PersonModel>>(emptyList())

    private val refreshing = MutableStateFlow(false)

    private val _uiState = MutableStateFlow<PersonScreenUiState>(PersonScreenUiState.Loading)
    val uiState: StateFlow<PersonScreenUiState>
        get() = _uiState

    var errorShowed: Boolean = false

    init {
        viewModelScope.launch {
            com.exercises.eventmanagment.commons.combine(
                _persons.flatMapLatest {
                    personInfoUseCase()
                },
                refreshing
            )  { persons,
                refreshing ->

                val uiState = if (refreshing) {
                    Log.d(TAG, "refresing: $refreshing")
                    PersonScreenUiState.Loading
                } else {
                    PersonScreenUiState.Ready(
                        persons = persons
                    )
                }
                uiState
            }.catch { throwable ->
                throwable.printStackTrace()
                _uiState.value = PersonScreenUiState.Error(throwable.message)
                Log.d(TAG, "error: ${throwable.message}")
            }.collect {
                _uiState.value = it
        }
        }
    }

    fun refresh(force: Boolean) {
        viewModelScope.launch {
            runCatching {
                refreshing.value = true
                delay(20)
                refreshing.value = false
            }
        }
    }

    companion object {
        const val TAG = "PersonPageViewModel"
    }
}

sealed interface PersonScreenUiState {
    data object Loading: PersonScreenUiState

    data class Error(
        val errorMessage: String? = null
    ): PersonScreenUiState

    data class Ready(
        val persons: List<PersonModel> = emptyList()
    ): PersonScreenUiState
}

