package com.exercises.eventmanagement.presentation.usecase

import com.exercises.eventmanagement.data.database.repositories.LocalRepository
import com.exercises.eventmanagement.data.mapper.toModel
import com.exercises.eventmanagement.presentation.domain.FurnitureModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class FurnitureInfoUseCase : KoinComponent  {
    private val localRepository: LocalRepository
        get() = get()

    operator fun invoke(): Flow<List<FurnitureModel>> = localRepository.getAllFurnitureFlow()
        .map { entities ->
            val models = entities.map { entity ->
                entity.toModel()
            }
            models
        }

}