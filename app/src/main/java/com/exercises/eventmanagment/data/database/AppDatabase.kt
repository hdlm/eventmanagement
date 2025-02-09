package com.exercises.eventmanagement.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
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


/**
 * La clase sera de tipo _Singleton_, por lo cual podra tener solamente una instancia
 */
@Database(entities = [
    PersonEntity::class,
    PersonSalaryEntity::class,
    EventEntity::class,
    PayrollEntity::class,
    FurnitureEntity::class,
    PayrollPersonSalaryEntity::class
    ], version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao
    abstract fun eventDao(): EventDao
    abstract fun payrollDao(): PayrollDao
    abstract fun furnitureDao(): FurnitureDao
    abstract fun payrollPersonSalaryDao(): PayrollPersonSalaryDao


    companion object {
        const val DATABASE_NAME = "events.db"

        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build().also { instance = it }
            }
        }

    }
}