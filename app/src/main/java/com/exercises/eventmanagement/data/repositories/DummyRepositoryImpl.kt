package com.exercises.eventmanagement.data.repositories

import com.exercises.eventmanagement.data.database.entities.EventEntity
import com.exercises.eventmanagement.data.database.entities.FurnitureEntity
import com.exercises.eventmanagement.data.database.entities.PayrollEntity
import com.exercises.eventmanagement.data.database.entities.PersonEntity
import com.exercises.eventmanagement.data.database.repositories.LocalRepository
import com.exercises.eventmanagement.data.database.entities.PayrollPersonSalaryEntity
import com.exercises.eventmanagement.data.database.entities.PersonSalaryEntity
import com.exercises.eventmanagement.data.database.entities.relations.PayrollWithPersonSalary
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class DummyRepositoryImpl : LocalRepository {
    val events = mutableListOf<EventEntity>(
        EventEntity(
            id = 1,
            name = "Fiestas Patronales",
            address = "Caracas",
            startEventdate = "01-10-2025",
            endEventdate = "01-10-2025"
        ),
        EventEntity(
            id = 2,
            name = "Fiestas Maritales",
            address = "Los Teques",
            startEventdate = "03-10-2025",
            endEventdate = "03-10-2025"
        ),
        EventEntity(
            id = 3,
            name = "Fiestas Nocturas",
            address = "Margarita",
            startEventdate = "05-10-2025",
            endEventdate = "07-10-2025"
        ),
    )

    override suspend fun insertEvent(event: EventEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteEvent(event: EventEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllEvents(): List<EventEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun getEventById(id: Int): EventEntity =
        events[0].copy(id = id)


    override fun getAllEventsFlow(): Flow<List<EventEntity>> =
        flowOf(events)

    val furnitures: List<FurnitureEntity> = listOf(
        FurnitureEntity(
            id = 1,
            description = "Mesa",
            price = 20.0
        ),
        FurnitureEntity(
            id = 2,
            description = "Sillas",
            price = 20.0
        ),
        FurnitureEntity(
            id = 1,
            description = "Adornos de mesa",
            price = 20.0
        ),
    )

    override suspend fun insertFurniture(person: FurnitureEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFurniture(person: FurnitureEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllFurniture(): List<FurnitureEntity> =
         furnitures

    override fun getAllFurnitureFlow(): Flow<List<FurnitureEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getFurnitureById(id: Int): FurnitureEntity {
        TODO("Not yet implemented")
    }

    val payrolls =
        listOf(
            PayrollWithPersonSalary(
                payroll = PayrollEntity(
                    id = 1,
                    personId = 101,
                    eventId = 1001,
                    salary = 1500.0
                ),
                persons = listOf(
                    PersonSalaryEntity(
                        id = 1,
                        personId = 1,
                        salary = 1500.0,
                        typeActivity = "staff"
                    ),
                    PersonSalaryEntity(
                        id = 2,
                        personId = 2,
                        salary = 1500.0,
                        typeActivity = "staff"
                    ),
                    PersonSalaryEntity(
                        id = 3,
                        personId = 3,
                        salary = 1500.0,
                        typeActivity = "staff"
                    ),
                )
            ),
            PayrollWithPersonSalary(
                payroll = PayrollEntity(
                    id = 2,
                    personId = 2,
                    eventId = 1002,
                    salary = 2000.0
                ),
                persons = listOf(
                    PersonSalaryEntity(
                        id = 2,
                        personId = 102,
                        salary = 2000.0,
                        typeActivity = "entertainment"
                    ),
                    PersonSalaryEntity(
                        id = 3,
                        personId = 102,
                        salary = 500.0,
                        typeActivity = "staff")
                )
            ),
            PayrollWithPersonSalary(
                payroll = PayrollEntity(
                    id = 3,
                    personId = 3,
                    eventId = 1003,
                    salary = 1800.0
                ),
                persons = listOf(
                    PersonSalaryEntity(
                        id = 4,
                        personId = 103,
                        salary = 1800.0,
                        typeActivity = "staff"
                    )
                )
            )
        )

    override suspend fun insertPayroll(person: PayrollEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deletePayroll(person: PayrollEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllPayroll(): List<PayrollEntity> {
        TODO("Not yet implemented")
    }

    override fun getAllPayrollFlow(): Flow<List<PayrollWithPersonSalary>> =
        flowOf(payrolls)

    override suspend fun getPayrollById(id: Int): PayrollEntity =
        payrolls[0].payroll.copy(id = id)

    override suspend fun getPayrollWithPersonSalaryById(id: Int): PayrollWithPersonSalary =
        payrolls[0].copy(payroll = PayrollEntity(
            id = id,
            personId = id,
            eventId = id,
            salary = id.toDouble()
        ))

    val persons = listOf(
        PersonEntity(
            id = 1,
            name = "Lucía",
            lastName = "Ramírez",
            gender = "Femenino",
            age = 28,
            phone = "0412-1234567",
            email = "lucia.ramirez@example.com"
        ),
        PersonEntity(
            id = 2,
            name = "Carlos",
            lastName = "Mendoza",
            gender = "Masculino",
            age = 35,
            phone = "0414-7654321",
            email = "carlos.mendoza@example.com"
        ),
        PersonEntity(
            id = 3,
            name = "Andrea",
            lastName = "Pérez",
            gender = "Femenino",
            age = 22,
            phone = "0426-9876543",
            email = "andrea.perez@example.com"
        )
    )
    
    override suspend fun insertPerson(person: PersonEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deletePerson(person: PersonEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllPerson(): List<PersonEntity> =
        persons

    override fun getAllPersonFlow(): Flow<List<PersonEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getPersonById(id: Int): PersonEntity {
        TODO("Not yet implemented")
    }

    override suspend fun insertPayrollPersonSalary(payrollPersonSalaryEntity: PayrollPersonSalaryEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deletePayrollPersonSalary(payrollPersonSalaryEntity: PayrollPersonSalaryEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllPayrollPersonSalary(): List<PayrollPersonSalaryEntity> {
        TODO("Not yet implemented")
    }

    override fun observableAllPayrollPersonSalary(): Flow<List<PayrollPersonSalaryEntity>> {
        TODO("Not yet implemented")
    }


}