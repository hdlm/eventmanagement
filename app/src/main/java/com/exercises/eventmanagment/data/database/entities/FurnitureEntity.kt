package com.exercises.eventmanagement.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "furniture")
data class FurnitureEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val description: String,
    val price: Double,
)
