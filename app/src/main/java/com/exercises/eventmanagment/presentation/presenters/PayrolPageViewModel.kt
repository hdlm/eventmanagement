package com.exercises.eventmanagment.presentation.presenters
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exercises.eventmanagement.data.database.entities.EventEntity
import com.exercises.eventmanagement.data.database.entities.PayrollEntity
import com.exercises.eventmanagement.data.database.entities.PersonEntity
import com.exercises.eventmanagement.data.database.repositories.LocalRepository
import com.exercises.eventmanagment.commons.onDismissType
import com.exercises.eventmanagment.data.mapper.toEntity
import com.exercises.eventmanagment.data.mapper.toModel
import com.exercises.eventmanagment.presentation.domain.PayrollModel
import com.exercises.eventmanagment.presentation.domain.PersonModel
import com.exercises.eventmanagment.presentation.usecase.EventInfoUseCase
import com.exercises.eventmanagment.presentation.usecase.PersonInfoUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PayrolPageViewModel: ViewModel(), KoinComponent {
    // Lazy injection
    private val localRepository: LocalRepository by inject()
    private val personInfoUseCase: PersonInfoUseCase by inject()
    private val eventInfoUseCase: EventInfoUseCase by inject()
    private lateinit var persons: List<PersonEntity>
    private lateinit var events: List<EventEntity>

/*
    val consulta : (Int) -> Unit = { id ->
        viewModelScope.launch {
            personInfoUseCase(id)
        }

    }
    fun ejecutarLaConsulta(id: Int) {
        consulta.invoke(id)
    }


    fun savePayroll(payroll: PayrollModel) {
        viewModelScope.launch {
            val payrollEntity = payroll.toEntity()  // mapeo de model a entity

            localRepository.insertPayroll(payrollEntity)
        }
    }

    fun getPersons (onDone: (Array<String>) -> Unit) = {
        viewModelScope.launch {
            val personsDeferred: Deferred<List<PersonEntity>> = getPersonsAsync(scope = this)
            val entities = personsDeferred.await()

            val models = entities.map { it.toModel() }
            val listOfPerson = models.map { it.name }

            onDone.invoke(listOfPerson.toTypedArray())
        }

    }

    /**
     * Traer el [Payroll]](PayRoll) por id
     * syntaxis de MarkDown
     */
    fun getPayroll(id: Int, onDone: (PayrollModel) -> Unit): Unit {
        viewModelScope.launch {
            val payrollDeferred = getPayrollAsync(id = id, scope = this)
            val personDeferred = getPersonAsync(id = id, scope = this)
//            val eventDeferred = getEventAsync(id = id, scope = this)

            val payroll = payrollDeferred.await()
            val person = personDeferred.await()
//            val event = eventDeferred.await()

            val model = PayrollModel(
                id = payroll.id,
                person = person.toModel(),
                salary = payroll.salary,
                event = event.toModel(),
                typeActivity = payroll.typeActivity
            )

            onDone.invoke(model)
        }
    }

    private fun getPayrollAsync(id: Int, scope: CoroutineScope): Deferred<PayrollEntity> = scope.async {
        localRepository.getPayrollById(id)
    }

    private fun getPersonAsync(id: Int, scope: CoroutineScope): Deferred<PersonEntity> = scope.async {
        personInfoUseCase.invoke(id)
    }
    private fun getPersonsAsync(scope: CoroutineScope): Deferred<List<PersonEntity>> = scope.async {
        personInfoUseCase.invoke()
    }

//    private fun getEventAsync(id: Int, scope: CoroutineScope): Deferred<EventEntity> = scope.async {
////        eventInfoUseCase.invoke(id)
//    }

    suspend fun mycoroutine() {
        viewModelScope.launch(Dispatchers.IO) {
            getNumeros()
            delay(100L)
        }
        viewModelScope.launch(Dispatchers.IO) {
            getLetras()
            delay(50L)
        }
    }

    suspend fun getNumeros() {
        for(n in 1..20) {
            Log.d(TAG, "getNumeros() -> n: $n")
            delay(100L)
        }

    }

    suspend fun getLetras() {
        for(c in 'a'..'m') {
            Log.d(TAG, "getLetras() -> c: $c")
            delay(50L)
        }
    }

    // traer todo el payroll
    suspend fun getAllPayroll() {
        viewModelScope.launch {
            localRepository.getAllPayroll()
        }
    }


 */
}

private const val TAG = "PayrollPageViewModel"