package com.exercises.eventmanagment.presentation.usecase

import com.exercises.eventmanagement.data.database.repositories.LocalRepository
import com.exercises.eventmanagment.data.mapper.toModel
import com.exercises.eventmanagment.presentation.domain.FurnitureModel
import com.exercises.eventmanagment.presentation.domain.PersonModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class PersonInfoUseCase : KoinComponent  {
    private val localRepository: LocalRepository
        get() = get()

    operator fun invoke(): Flow<List<PersonModel>> = localRepository.getAllPersonFlow()
        .map { entities ->
            val persons = entities.map { entity ->
                entity.toModel()
            }
             persons
        }

}