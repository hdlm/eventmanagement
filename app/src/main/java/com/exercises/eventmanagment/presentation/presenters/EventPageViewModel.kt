package com.exercises.eventmanagment.presentation.presenters

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exercises.eventmanagment.presentation.domain.EventModel
import com.exercises.eventmanagment.presentation.usecase.EventInfoUseCase
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
class EventPageViewModel : ViewModel(), KoinComponent {
    private val eventInfoUseCase: EventInfoUseCase by inject()

    private val _events = MutableStateFlow<List<EventModel>>(emptyList())

    private val refreshing = MutableStateFlow(false)

    private val _uiState = MutableStateFlow<EventScreenUiState>(EventScreenUiState.Loading)
    val uiState: StateFlow<EventScreenUiState>
        get() = _uiState

    var errorShowed: Boolean = false

    init {
        viewModelScope.launch {
            com.exercises.eventmanagment.commons.combine(
                _events.flatMapLatest {
                    eventInfoUseCase()
                },
                refreshing
            ) { events,
                refrshing ->

                val uiState = if (refrshing) {
                    Log.d(TAG, "refreshing: $refrshing")
                    EventScreenUiState.Loading
                } else {
                    EventScreenUiState.Ready(
                        events = events
                    )
                }
                uiState

            }.catch { throwable ->
                throwable.printStackTrace()
                _uiState.value = EventScreenUiState.Error(throwable.message)
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
        const val TAG = "EventPageViewModel"
    }
}

sealed interface EventScreenUiState {
    data object Loading: EventScreenUiState

    data class Error(
        val errorMessage: String? = null
    ) : EventScreenUiState

    data class Ready (
        val events: List<EventModel> = emptyList()
    ) : EventScreenUiState
}