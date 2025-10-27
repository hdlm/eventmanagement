package com.exercises.eventmanagement.presentation.domain

data class PersonSalaryModel(
    val id: Int?,
    val person: PersonModel,
    val salary: Double,
    val typeActivity: String, // staff o entertaiment
    val payrollId: Int?
)
