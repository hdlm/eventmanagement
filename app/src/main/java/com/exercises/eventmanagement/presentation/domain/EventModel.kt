package com.exercises.eventmanagment.presentation.domain

data class EventModel(
    val id: Int? = null,
    val name: String,
    val address: String,
    val startEventdate: String,
    val endEventdate: String,
)
