package com.exercises.eventmanagment.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



//TODO agregar los Constraint: ForeignKey, Index
@Entity
data class PersonSalaryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "person_id") val personId: Int?,
    val salary: Double,
    @ColumnInfo(name = "type_activity") val typeActivity: String, // staff o entertaiment
)


//TODO crear la clase Mapper para esta conversion de objetos (entity - model)
