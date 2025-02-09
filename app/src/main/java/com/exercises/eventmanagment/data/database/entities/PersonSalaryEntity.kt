package com.exercises.eventmanagment.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.exercises.eventmanagement.data.database.entities.PayrollEntity
import com.exercises.eventmanagement.data.database.entities.PersonEntity


@Entity(tableName = "person_salary",
    foreignKeys = [
        ForeignKey(
            entity = PersonEntity::class,
            parentColumns = ["id"],
            childColumns = ["person_id"],
            onDelete = ForeignKey.Companion.NO_ACTION
        )
    ]
)
data class PersonSalaryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "person_id") val personId: Int?,
    val salary: Double,
    @ColumnInfo(name = "type_activity") val typeActivity: String, // staff o entertaiment
)


//TODO crear la clase Mapper para esta conversion de objetos (entity - model)
