package com.exercises.eventmanagment.data.mapper

import com.exercises.eventmanagement.data.database.entities.PersonEntity
import com.exercises.eventmanagment.presentation.domain.PersonModel

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
