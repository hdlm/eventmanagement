package com.exercises.eventmanagement.data.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.exercises.eventmanagement.data.database.entities.PayrollPersonSalaryEntity
import com.exercises.eventmanagement.data.database.entities.relations.PayrollWithPersonSalary
import kotlinx.coroutines.flow.Flow

@Dao
interface PayrollPersonSalaryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPayrollPersonSalary(payrollPersonSalary: PayrollPersonSalaryEntity)

    @Delete
    suspend fun deletePayrollPersonSalary(payrollPersonSalaryDao: PayrollPersonSalaryEntity)

    @Query("SELECT * FROM payroll_person_salary WHERE id = :id")
    suspend fun getPayrollPersonSalaryById(id: Int): PayrollWithPersonSalary

    @Query("SELECT * FROM payroll_person_salary")
    suspend fun getAllPayrollPersonSalary(): List<PayrollPersonSalaryEntity>


    @Query("SELECT * FROM payroll_person_salary")
    fun observableAllPayrollPersonSalary(): Flow<List<PayrollPersonSalaryEntity>>
}