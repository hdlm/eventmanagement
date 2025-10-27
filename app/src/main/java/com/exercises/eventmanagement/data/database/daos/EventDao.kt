package com.exercises.eventmanagement.data.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.exercises.eventmanagement.data.database.entities.EventEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(event: EventEntity)

    @Delete
    suspend fun deleteEvent(event: EventEntity)

    @Query("SELECT * FROM event")
    suspend fun getAllEvent(): List<EventEntity>


    @Query("SELECT * FROM event WHERE id = :id")
    suspend fun getEventById(id: Int): EventEntity


    @Query("SELECT * FROM event")
    fun observableEvents(): Flow<List<EventEntity>>

}