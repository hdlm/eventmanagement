package com.exercises.eventmanagment.presentation.presenters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exercises.eventmanagement.data.database.entities.PersonEntity
import com.exercises.eventmanagement.data.database.repositories.LocalRepository
import com.exercises.eventmanagment.data.mapper.toEntity
import com.exercises.eventmanagment.presentation.domain.PersonModel
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PersonAddViewModel: ViewModel(), KoinComponent {
    private val localRepository: LocalRepository by inject()

    fun savePerson(person: PersonModel) {
        viewModelScope.launch {
                localRepository.insertPerson(person.toEntity())
        }
    }

    //traer persona por id
    fun getPerson(id: Int) {
        viewModelScope.launch {
            localRepository.getPersonById(id)
        }
    }

    //traer todas las personas
    fun getAllPersons() {
        viewModelScope.launch {
            localRepository.getAllPerson()
        }
    }
}