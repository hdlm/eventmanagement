package com.exercises.eventmanagement.presentation.usecase

import com.exercises.eventmanagement.data.database.repositories.LocalRepository
import com.exercises.eventmanagement.data.mapper.toModel
import com.exercises.eventmanagement.presentation.domain.FurnitureModel
import com.exercises.eventmanagement.presentation.domain.PersonModel
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