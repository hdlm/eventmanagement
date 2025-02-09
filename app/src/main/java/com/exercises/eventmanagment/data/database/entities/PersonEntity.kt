package com.exercises.eventmanagement.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person")
data class PersonEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val name: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    val gender: String,
    val age: Int,
    val phone: String,
    val email: String,
)
