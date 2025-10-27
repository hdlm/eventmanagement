package com.exercises.eventmanagement.presentation.presenters

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exercises.eventmanagement.data.database.entities.EventEntity
import com.exercises.eventmanagement.data.database.repositories.LocalRepository
import com.exercises.eventmanagement.presentation.domain.EventModel
import com.exercises.eventmanagement.presentation.usecase.EventInfoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddEventViewModel : ViewModel(), KoinComponent {
    private val localRepository: LocalRepository by inject()

    fun saveEvent(event: EventModel) {
        Log.d(TAG, "saveEvent called with: name=${event.name}, address=${event.address}")
        viewModelScope.launch {
            try {
                val eventEntity = EventEntity(
                    id = null,
                    name = event.name,
                    startEventdate = event.startEventdate,
                    endEventdate = event.endEventdate,
                    address = event.address
                )
                localRepository.insertEvent(eventEntity)
            } catch (e: Exception) {
                Log.e(TAG, "Error saving event: ${e.message}", e)
            }
        }
    }

    companion object {
        const val TAG = "AddEventViewModel"
    }
}