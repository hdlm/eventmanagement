package com.exercises.eventmanagement.presentation.presenters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exercises.eventmanagement.data.repositories.DummyRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ListadoViewModel: ViewModel(), KoinComponent {
    private val repository: DummyRepository by inject()

    private val _muebles = repository.getMuebles()
    val muebles: StateFlow<List<String>>
        get() = _muebles.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), emptyList())

}