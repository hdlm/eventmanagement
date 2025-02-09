package com.exercises.eventmanagment.commons

import androidx.room.TypeConverter
import com.exercises.eventmanagement.commons.CommonValues.TypeActivity

class Converters {
    @TypeConverter
    fun fromTypeActivity(value: TypeActivity): String {
        return value.value
    }

    @TypeConverter
    fun toTypeActivity(value: String): TypeActivity {
        return when (value) {
            "staff" -> TypeActivity.STAFF
            "entertainment" -> TypeActivity.ENTERTAINMENT
            else -> throw IllegalArgumentException("Invalid type activity")
        }
    }
}