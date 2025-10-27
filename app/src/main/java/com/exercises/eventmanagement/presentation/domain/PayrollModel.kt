package com.exercises.eventmanagment.presentation.domain

data class PayrollModel(
    val id: Int?,
    val event: EventModel,
    val persons: List<PersonSalaryModel>
)
