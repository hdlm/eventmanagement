package com.exercises.eventmanagment.presentation.presenters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exercises.eventmanagement.data.database.repositories.LocalRepository
import com.exercises.eventmanagment.data.mapper.toEntity
import com.exercises.eventmanagment.presentation.domain.FurnitureModel
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FurnitureAddPageViewModel : ViewModel(), KoinComponent {
    private val localRepository: LocalRepository by inject()

    fun saveFurniture(furniture: FurnitureModel) {
        viewModelScope.launch {
            localRepository.insertFurniture(furniture.toEntity())
        }
    }

    // traer imobiliario por id
    fun getFurniture(id: Int) {
        viewModelScope.launch {
            localRepository.getFurnitureById(id)
        }
    }

    // traer todo el inmobiliario
    fun getAllFurniture() {
        viewModelScope.launch {
            localRepository.getAllFurniture()
        }
    }
}