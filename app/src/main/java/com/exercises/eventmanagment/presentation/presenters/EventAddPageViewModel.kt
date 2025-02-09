package com.exercises.eventmanagement.presentation.presenters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exercises.eventmanagement.data.database.entities.EventEntity
import com.exercises.eventmanagement.data.database.repositories.LocalRepository
import com.exercises.eventmanagment.presentation.domain.EventModel
import com.exercises.eventmanagment.presentation.usecase.EventInfoUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class EventAddPageViewModel : ViewModel(), KoinComponent {
    private val localRepository: LocalRepository by inject()
    private val eventInfoUseCase: EventInfoUseCase by inject()

    val events = getAllEvents()

    fun saveEvent(event: EventModel) {
        viewModelScope.launch {
            // mapear el model a entidad del objeto Event
            val eventEntity = EventEntity(
                id = null,
                name = event.name,
                startEventdate = event.startEventdate,
                endEventdate = event.endEventdate,
                address = event.address
            )
            localRepository.insertEvent(eventEntity)
        }
    }

    // traer evento por id
    fun getEvent(id: Int) {
        viewModelScope.launch {
            localRepository.getEventById(id)
        }
    }

    // traer todos los eventos
    private fun getAllEvents(): Flow<List<EventModel>> =
        eventInfoUseCase()

}