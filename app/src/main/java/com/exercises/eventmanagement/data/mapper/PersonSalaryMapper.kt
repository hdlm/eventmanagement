package com.exercises.eventmanagment.data.mapper

import com.exercises.eventmanagment.data.database.entities.PersonSalaryEntity
import com.exercises.eventmanagment.presentation.domain.PersonSalaryModel

fun PersonSalaryModel.toEntity() =
    PersonSalaryEntity(
        id = id,
        personId = person.id,
        salary = salary,
        typeActivity = typeActivity,
    )
