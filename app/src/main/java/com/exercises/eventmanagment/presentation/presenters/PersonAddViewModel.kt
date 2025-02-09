package com.exercises.eventmanagment.presentation.presenters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exercises.eventmanagement.data.database.entities.PersonEntity
import com.exercises.eventmanagement.data.database.repositories.LocalRepository
import com.exercises.eventmanagment.presentation.domain.PersonModel
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PersonAddViewModel: ViewModel(), KoinComponent {
    private val localRepository: LocalRepository by inject()

    fun savePerson(person: PersonModel) {
        viewModelScope.launch {
            //mapeo del model a entidad del objeto person
            val personEntity = PersonEntity(
                id = null,
                name = person.name,
                lastName = person.lastName,
                gender = person.gender,
                age = person.age,
                phone = person.phone,
                email = person.email
            )
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