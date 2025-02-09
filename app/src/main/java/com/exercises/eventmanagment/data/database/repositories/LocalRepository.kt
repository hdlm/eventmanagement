package com.exercises.eventmanagement.data.database.repositories

import com.exercises.eventmanagement.data.database.entities.EventEntity
import com.exercises.eventmanagement.data.database.entities.FurnitureEntity
import com.exercises.eventmanagement.data.database.entities.PayrollEntity
import com.exercises.eventmanagement.data.database.entities.PersonEntity
import com.exercises.eventmanagment.data.database.entities.PayrollPersonSalaryEntity
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent

interface LocalRepository : KoinComponent {

    suspend fun insertEvent(event: EventEntity)
    suspend fun deleteEvent(event: EventEntity)
    suspend fun getAllEvents(): List<EventEntity>
    suspend fun getEventById(id: Int): EventEntity
    fun getAllEventsFlow(): Flow<List<EventEntity>>

    suspend fun insertFurniture(person: FurnitureEntity)
    suspend fun deleteFurniture(person: FurnitureEntity)
    suspend fun getAllFurniture(): List<FurnitureEntity>
    fun getAllFurnitureFlow(): Flow<List<FurnitureEntity>>
    suspend fun getFurnitureById(id: Int): FurnitureEntity

    suspend fun insertPayroll(person: PayrollEntity)
    suspend fun deletePayroll(person: PayrollEntity)
    suspend fun getAllPayroll(): List<PayrollEntity>
    suspend fun getPayrollById(id: Int): PayrollEntity

    suspend fun insertPerson(person: PersonEntity)
    suspend fun deletePerson(person: PersonEntity)
    suspend fun getAllPerson(): List<PersonEntity>
    fun getAllPersonFlow(): Flow<List<PersonEntity>>
    suspend fun getPersonById(id: Int): PersonEntity

    suspend fun insertPayrollPersonSalary(payrollPersonSalaryEntity: PayrollPersonSalaryEntity)
    suspend fun deletePayrollPersonSalary(payrollPersonSalaryEntity: PayrollPersonSalaryEntity)
    suspend fun getAllPayrollPersonSalary(): List<PayrollPersonSalaryEntity>
    fun observableAllPayrollPersonSalary(): Flow<List<PayrollPersonSalaryEntity>>



    













}