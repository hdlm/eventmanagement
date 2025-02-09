package com.exercises.eventmanagment.data.mapper

import com.exercises.eventmanagement.data.database.entities.PayrollEntity
import com.exercises.eventmanagment.presentation.domain.PayrollModel


fun PayrollModel.toEntity() =
    PayrollEntity(
        id = id,
        personId = TODO(),
        eventId = TODO(),
        salary = TODO(),
        typeActivity = TODO()
    )



/**
 * Relacion muchos a muchos
 * Se soluciona creando una tabla intermedia para asociar ambas entidades
 */




//fun PayrollEntity.toModel () =
//    PayrollModel(
//        id = id,
//        event = eventId,
//        persons = personId
//    )
