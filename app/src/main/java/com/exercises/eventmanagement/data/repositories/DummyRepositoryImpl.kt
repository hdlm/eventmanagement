package com.exercises.eventmanagement.data.repositories

import com.exercises.eventmanagement.data.database.entities.EventEntity
import com.exercises.eventmanagement.data.database.entities.FurnitureEntity
import com.exercises.eventmanagement.data.database.entities.PayrollEntity
import com.exercises.eventmanagement.data.database.entities.PayrollPersonSalaryEntity
import com.exercises.eventmanagement.data.database.entities.PersonEntity
import com.exercises.eventmanagement.data.database.entities.relations.PayrollWithPersonSalary
import com.exercises.eventmanagement.data.database.repositories.LocalRepository
import kotlinx.coroutines.flow.Flow
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

    override suspend fun insertFurniture(person: FurnitureEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFurniture(person: FurnitureEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllFurniture(): List<FurnitureEntity> {
        TODO("Not yet implemented")
    }

    override fun getAllFurnitureFlow(): Flow<List<FurnitureEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getFurnitureById(id: Int): FurnitureEntity {
        TODO("Not yet implemented")
    }

    override suspend fun insertPayroll(person: PayrollEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deletePayroll(person: PayrollEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllPayroll(): List<PayrollEntity> {
        TODO("Not yet implemented")
    }

    override fun getAllPayrollFlow(): Flow<List<PayrollWithPersonSalary>> {
        TODO("Not yet implemented")
    }

    override suspend fun getPayrollById(id: Int): PayrollEntity {
        TODO("Not yet implemented")
    }

    override suspend fun insertPerson(person: PersonEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deletePerson(person: PersonEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllPerson(): List<PersonEntity> {
        TODO("Not yet implemented")
    }

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