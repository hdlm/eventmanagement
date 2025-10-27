package com.exercises.eventmanagement.data.mapper

import com.exercises.eventmanagement.data.database.entities.EventEntity
import com.exercises.eventmanagement.presentation.domain.EventModel

fun EventEntity.toModel() =
    EventModel(
        id = id,
        name = name,
        address = address,
        startEventdate = startEventdate,
        endEventdate = endEventdate
    )
