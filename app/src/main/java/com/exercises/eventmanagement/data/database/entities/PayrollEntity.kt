package com.exercises.eventmanagement.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.NO_ACTION
import androidx.room.Index
import androidx.room.PrimaryKey
import com.exercises.eventmanagment.data.database.entities.PersonSalaryEntity

/**
 * Relacion:
 * nomina --   evento
 * nomima --+  persona
 * */
@Entity(tableName = "payroll",
    foreignKeys = [
        ForeignKey(entity = PersonSalaryEntity::class,
            parentColumns = ["id"],
            childColumns = ["person_id"],
            onDelete = NO_ACTION ),
        ForeignKey(entity = EventEntity::class,
            parentColumns = ["id"],
            childColumns = ["event_id"],
            onDelete = NO_ACTION ),
    ],
    indices = [
        Index("person_id"),
        Index("event_id")
    ]
)
data class PayrollEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "person_id") val personId: Int?,
    @ColumnInfo(name = "event_id") val eventId: Int?,
    val salary: Double,
)
