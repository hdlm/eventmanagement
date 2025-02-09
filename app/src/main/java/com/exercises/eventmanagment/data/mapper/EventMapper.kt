package com.exercises.eventmanagment.data.mapper

import com.exercises.eventmanagement.data.database.entities.EventEntity
import com.exercises.eventmanagment.presentation.domain.EventModel

fun EventEntity.toModel() =
    EventModel(
        id = id,
        name = name,
        address = address,
        startEventdate = startEventdate,
        endEventdate = endEventdate
    )
