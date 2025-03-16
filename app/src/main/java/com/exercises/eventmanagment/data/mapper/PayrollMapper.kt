package com.exercises.eventmanagment.data.mapper

import com.exercises.eventmanagement.data.database.entities.PayrollEntity
import com.exercises.eventmanagment.presentation.domain.EventModel
import com.exercises.eventmanagment.presentation.domain.PayrollModel
import com.exercises.eventmanagment.presentation.domain.PersonSalaryModel


fun PayrollModel.toEntity() =
    PayrollEntity(
        id = id,
        personId = TODO(),
        eventId = TODO(),
        salary = TODO(),
    )

/**
 * Relacion muchos a muchos
 * Se soluciona creando una tabla intermedia para asociar ambas entidades
 */


fun PayrollModel.toEntities(): List<PayrollEntity> {
    return this.persons.map { person ->
        PayrollEntity(
            id = id,
            personId = person.id,
            eventId = event.id,
            salary = person.salary,
        )
    }
}


//fun PayrollEntity.toModel () =
//    PayrollModel(
//        id = id,
//        event = eventId,
//        persons = personId
//    )
