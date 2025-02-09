package com.exercises.eventmanagement.data.database.repositories

import com.exercises.eventmanagement.commons.CommonValues.context
import com.exercises.eventmanagement.data.database.AppDatabase
import com.exercises.eventmanagement.data.database.daos.EventDao
import com.exercises.eventmanagement.data.database.daos.FurnitureDao
import com.exercises.eventmanagement.data.database.daos.PayrollDao
import com.exercises.eventmanagement.data.database.daos.PersonDao
import com.exercises.eventmanagement.data.database.entities.EventEntity
import com.exercises.eventmanagement.data.database.entities.FurnitureEntity
import com.exercises.eventmanagement.data.database.entities.PayrollEntity
import com.exercises.eventmanagement.data.database.entities.PersonEntity
import com.exercises.eventmanagment.data.database.daos.PayrollPersonSalaryDao
import com.exercises.eventmanagment.data.database.entities.PayrollPersonSalaryEntity
import com.exercises.eventmanagment.data.database.entities.PersonSalaryEntity
import kotlinx.coroutines.flow.Flow

class LocalRepositoryImpl : LocalRepository {

    private val eventDao: EventDao = AppDatabase.getInstance(context).eventDao()
    private val furnitureDao: FurnitureDao = AppDatabase.getInstance(context).furnitureDao()
    private val payrollDao: PayrollDao = AppDatabase.getInstance(context).payrollDao()
    private val personDao: PersonDao = AppDatabase.getInstance(context).personDao()
    private val personSalaryDao: PayrollDao = AppDatabase.getInstance(context).payrollDao()
    private val payrollPersonSalaryDao: PayrollPersonSalaryDao = AppDatabase.getInstance(context).payrollPersonSalaryDao()


    override suspend fun insertEvent(event: EventEntity) {
        eventDao.insertEvent(event)
    }

    override suspend fun deleteEvent(event: EventEntity) {
        eventDao.deleteEvent(event)
    }

    override suspend fun getAllEvents(): List<EventEntity> =
        eventDao.getAllEvent()

    override suspend fun getEventById(id: Int): EventEntity =
        eventDao.getEventById(id)


    override fun getAllEventsFlow(): Flow<List<EventEntity>> =
        eventDao.observableEvents()



    override suspend fun insertFurniture(person: FurnitureEntity) {
        furnitureDao.insertFurniture(person)
    }

    override suspend fun deleteFurniture(person: FurnitureEntity) {
        furnitureDao.deleteFurniture(person)
    }

    override suspend fun getAllFurniture(): List<FurnitureEntity> =
        furnitureDao.getAllFurniture()


    override fun getAllFurnitureFlow(): Flow<List<FurnitureEntity>> =
        furnitureDao.observeAllFurniture()

    override suspend fun getFurnitureById(id: Int): FurnitureEntity =
        furnitureDao.getFurnitureById(id)

    override suspend fun insertPayroll(person: PayrollEntity) {
        payrollDao.insertPayroll(person)
    }

    override suspend fun deletePayroll(person: PayrollEntity) {
        payrollDao.deletePayroll(person)
    }

    override suspend fun getAllPayroll(): List<PayrollEntity> =
        payrollDao.getAllPayroll()

    suspend fun getAllPersonSalary(): List<PersonSalaryEntity> =
        payrollDao.getAllPersonSalary()

    override suspend fun getPayrollById(id: Int): PayrollEntity =
        payrollDao.getPayrollById(id)


    override suspend fun insertPerson(person: PersonEntity) {
        personDao.insertPerson(person)
    }

    override suspend fun deletePerson(person: PersonEntity) {
        personDao.deletePerson(person)
    }

    override suspend fun getAllPerson(): List<PersonEntity> =
        personDao.getAllPerson()

    override fun getAllPersonFlow(): Flow<List<PersonEntity>> =
        personDao.observeAllPersons()

    override suspend fun getPersonById(id: Int): PersonEntity =
        personDao.getPersonById(id)

    override suspend fun insertPayrollPersonSalary(payrollPersonSalaryEntity: PayrollPersonSalaryEntity) =
        payrollPersonSalaryDao.insertPayrollPersonSalary(payrollPersonSalaryEntity)

    override suspend fun deletePayrollPersonSalary(payrollPersonSalaryEntity: PayrollPersonSalaryEntity) =
        payrollPersonSalaryDao.deletePayrollPersonSalary(payrollPersonSalaryEntity)

    override suspend fun getAllPayrollPersonSalary(): List<PayrollPersonSalaryEntity> =
        payrollPersonSalaryDao.getAllPayrollPersonSalary()

    override fun observableAllPayrollPersonSalary(): Flow<List<PayrollPersonSalaryEntity>> =
        payrollPersonSalaryDao.observableAllPayrollPersonSalary()


}