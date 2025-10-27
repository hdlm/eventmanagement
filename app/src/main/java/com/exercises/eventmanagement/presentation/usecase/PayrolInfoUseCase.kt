package com.exercises.eventmanagement.presentation.usecase

import com.exercises.eventmanagement.data.database.entities.PayrollEntity
import com.exercises.eventmanagement.data.database.repositories.LocalRepository
import com.exercises.eventmanagement.data.database.entities.relations.PayrollWithPersonSalary
import com.exercises.eventmanagement.presentation.domain.EventModel
import com.exercises.eventmanagement.presentation.domain.PayrollModel
import com.exercises.eventmanagement.presentation.domain.PersonModel
import com.exercises.eventmanagement.presentation.domain.PersonSalaryModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class PayrollInfoUseCase : KoinComponent  {
    private val localRepository: LocalRepository
        get() = get()

    operator fun invoke(): Flow<List<PayrollWithPersonSalary>> =
        localRepository.getAllPayrollFlow()
}

