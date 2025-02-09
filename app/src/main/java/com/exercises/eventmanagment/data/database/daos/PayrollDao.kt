package com.exercises.eventmanagement.data.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.exercises.eventmanagement.data.database.entities.PayrollEntity
import com.exercises.eventmanagment.data.database.entities.PersonSalaryEntity

@Dao
interface PayrollDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPayroll(payroll: PayrollEntity)

    @Delete
    suspend fun deletePayroll(payroll: PayrollEntity)

    @Query("SELECT * FROM payroll")
    suspend fun getAllPayroll(): List<PayrollEntity>

    @Query("SELECT * FROM payroll")
    suspend fun getAllPersonSalary(): List<PersonSalaryEntity>


    @Query("SELECT * FROM payroll WHERE id = :id")
    suspend fun getPayrollById(id: Int): PayrollEntity

}