package com.exercises.eventmanagment.data.repositories

import com.exercises.eventmanagement.data.database.repositories.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class DummyRepositoryImpl : DummyRepository {
    override fun getMuebles(): Flow<List<String>> {
        val muebles = listOf("Sillas", "Mesas", "Adorno de Mesa", "Adorno de Entrada", "Adorno de pasillo", "Adorno de Baño", "Silla plegables")
        return flowOf(muebles)
    }


}