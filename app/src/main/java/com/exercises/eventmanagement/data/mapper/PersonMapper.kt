package com.exercises.eventmanagement.data.mapper

import com.exercises.eventmanagement.data.database.entities.PersonEntity
import com.exercises.eventmanagement.presentation.domain.PersonModel

fun PersonEntity.toModel() =
    PersonModel(
        id = id,
        name = name,
        lastName = lastName,
        gender = gender,
        age = age,
        phone = phone,
        email = email
    )
