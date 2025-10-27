package com.exercises.eventmanagement.data.repositories

import kotlinx.coroutines.flow.Flow

//TODO eliminar esta clase y utilizar [DummyRepositoryImpl]
interface DummyRepository {
    fun getMuebles(): Flow<List<String>>
}