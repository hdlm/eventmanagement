package com.exercises.eventmanagement.data.mapper

import com.exercises.eventmanagement.data.database.entities.PersonSalaryEntity
import com.exercises.eventmanagement.presentation.domain.PersonSalaryModel


fun PersonSalaryModel.toEntity() =
    PersonSalaryEntity(
        id = id,
        personId = person.id,
        salary = salary,
        typeActivity = typeActivity,
    )
