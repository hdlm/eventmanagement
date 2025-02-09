package com.exercises.eventmanagment.data.database.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.exercises.eventmanagement.data.database.entities.PayrollEntity
import com.exercises.eventmanagment.data.database.entities.PersonSalaryEntity

data class PayrollWithPersonSalary(

    @Embedded
    val payroll: PayrollEntity,
    @Relation(
        parentColumn = "payroll_id",
        entityColumn = "person_id"
    )
    val persons: List<PersonSalaryEntity>
)
