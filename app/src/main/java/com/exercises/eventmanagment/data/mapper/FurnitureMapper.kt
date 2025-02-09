package com.exercises.eventmanagment.data.mapper

import com.exercises.eventmanagement.data.database.entities.FurnitureEntity
import com.exercises.eventmanagment.presentation.domain.FurnitureModel

fun FurnitureModel.toEntity() =
    FurnitureEntity(
        id = id,
        description = description,
        price = price
    )

fun FurnitureEntity.toModel() =
    FurnitureModel(
        id = id,
        description = description,
        price = price
    )
