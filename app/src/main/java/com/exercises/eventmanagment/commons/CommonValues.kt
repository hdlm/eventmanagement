package com.exercises.eventmanagement.commons

import android.content.Context

object CommonValues {
    enum class TypeActivity(val value: String) {
        STAFF("staff"),
        ENTERTAINMENT("entertainment")
    }

    lateinit var context: Context
}