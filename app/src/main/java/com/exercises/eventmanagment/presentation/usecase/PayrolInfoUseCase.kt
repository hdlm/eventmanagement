package com.exercises.eventmanagment.presentation.usecase

import com.exercises.eventmanagement.data.database.entities.PayrollEntity
import com.exercises.eventmanagement.data.database.repositories.LocalRepository
import com.exercises.eventmanagment.presentation.domain.EventModel
import com.exercises.eventmanagment.presentation.domain.PayrollModel
import com.exercises.eventmanagment.presentation.domain.PersonModel
import com.exercises.eventmanagment.presentation.domain.PersonSalaryModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class PayrollInfoUseCase : KoinComponent  {
    private val localRepository: LocalRepository
        get() = get()

    operator fun invoke(): Flow<List<PayrollModel>> =
        localRepository.getAllPayrollFlow()
            .map { entities ->
                val models = entities.map { entity ->
                    entity.toModel()
                }
                models
            }

}

//class PayrollInfoUseCase(
//    private val localRepository: LocalRepository,
//) : KoinComponent {
//
//    operator fun invoke(): Flow<List<PayrollModel>> =
//        localRepository.getAllPayrollFlow()
//            .map { entities: List<PayrollEntity> ->
//                // Obtener los eventos y personas relacionados
//                val events = localRepository.getAllEvents()
//                val persons = localRepository.getAllPerson()
//
//                // Mapear cada PayrollEntity a PayrollModel
//                val models = entities.map { entity: PayrollEntity ->
//                    val event = events.find { it.id == entity.eventId }
//                    val personList = persons.filter { it.id == entity.personId }
//
//                    PayrollModel(
//                        id = entity.id,
//                        event = EventModel(
//                            id = entity.eventId,
//                            name = "Por Definirse",
//                            address = "Por Definirse",
//                            startEventdate = "Por Definirse",
//                            endEventdate = "Por Definirse",
//                        ),
//                        persons = personList.map { person ->
//                            PersonSalaryModel(id = person.id,
//                                person = PersonModel(
//                                    id = person.id,
//                                    name = "Por Definirse",
//                                    lastName = "Por Definirse",
//                                    gender = "Por Definirse",
//                                    age = 18,
//                                    phone = "Por Definirse",
//                                    email = "Por Definirse"
//                                ),
//                                salary = entity.salary,
//                                typeActivity = "Por Definirse",
//                                payrollId = null)
//
//                        }
//                    )
//                }
//                models
//            }
//}

