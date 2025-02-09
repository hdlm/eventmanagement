package com.exercises.eventmanagment.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.exercises.eventmanagement.data.database.entities.PayrollEntity

/**
 * La clase es responsable de la relacion entre las tablas:
 * payroll  y person_salary, ya que dicha relacion es de muchos a muchos.
 */
@Entity( tableName = "payroll_person_salary",
    foreignKeys = [
        ForeignKey(
            entity = PayrollEntity::class,
            parentColumns = ["id"],
            childColumns = ["payroll_id"],
            onDelete = ForeignKey.Companion.NO_ACTION
        ),
        ForeignKey(
            entity = PersonSalaryEntity::class,
            parentColumns = ["id"],
            childColumns = ["person_salary_id"],
            onDelete = ForeignKey.Companion.NO_ACTION
        ),
    ],
    indices = [
        Index("payroll_id"),
        Index("person_salary_id")
    ]
)
data class PayrollPersonSalaryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "payroll_id") val payrollId: Int,
    @ColumnInfo(name = "person_salary_id") val personSalaryId: Int,
)