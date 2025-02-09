package com.exercises.eventmanagement.data.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.exercises.eventmanagement.data.database.entities.FurnitureEntity
import com.exercises.eventmanagement.data.database.entities.PersonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerson(person: PersonEntity)

    @Delete
    suspend fun deletePerson(person: PersonEntity)

    @Query("SELECT * FROM person")
    suspend fun getAllPerson(): List<PersonEntity>

    @Query("SELECT * FROM person")
    fun observeAllPersons(): Flow<List<PersonEntity>>


    @Query("SELECT * FROM person WHERE id = :id")
    suspend fun getPersonById(id: Int): PersonEntity


}