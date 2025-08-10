package com.exercises.eventmanagment.data.repositories

import kotlinx.coroutines.flow.Flow

interface DummyRepository {
    fun getMuebles(): Flow<List<String>>
}