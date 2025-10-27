package com.exercises.eventmanagement.data.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.exercises.eventmanagement.data.database.entities.FurnitureEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FurnitureDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFurniture(furniture: FurnitureEntity)

    @Delete
    suspend fun deleteFurniture(furniture: FurnitureEntity)

    @Query("SELECT * FROM furniture")
    suspend fun getAllFurniture(): List<FurnitureEntity>

    @Query("SELECT * FROM furniture")
    fun observeAllFurniture(): Flow<List<FurnitureEntity>>

    @Query("SELECT * FROM furniture WHERE id = :id")
    suspend fun getFurnitureById(id: Int): FurnitureEntity

}