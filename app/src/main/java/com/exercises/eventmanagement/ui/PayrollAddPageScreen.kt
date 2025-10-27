package com.exercises.eventmanagement.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.exercises.eventmanagement.R
import com.exercises.eventmanagement.data.database.entities.EventEntity
import com.exercises.eventmanagement.data.database.entities.PersonEntity
import com.exercises.eventmanagement.data.database.repositories.LocalRepository
import com.exercises.eventmanagement.data.mapper.toEntity
import com.exercises.eventmanagement.data.mapper.toModel
import com.exercises.eventmanagement.data.repositories.DummyRepositoryImpl
import com.exercises.eventmanagement.presentation.domain.EventModel
import com.exercises.eventmanagement.presentation.domain.PayrollModel
import com.exercises.eventmanagement.presentation.domain.PersonModel
import com.exercises.eventmanagement.presentation.domain.PersonSalaryModel
import com.exercises.eventmanagement.presentation.presenters.AddPayrollScreenUiState
import com.exercises.eventmanagement.presentation.presenters.PayrollAddPageViewModel
import com.exercises.eventmanagement.ui.components.ComboBox
import com.exercises.eventmanagement.ui.navigation.Screens
import com.exercises.eventmanagement.ui.theme.EventManagementTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject
import org.koin.dsl.module


@Composable
fun PayrollAddPageScreen(
    viewModel: PayrollAddPageViewModel = koinViewModel()
) {
  val payrollAddScreenUiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val uiState = payrollAddScreenUiState) {
        is AddPayrollScreenUiState.Loading ->
            PayrollAddScreenLoading()

        is AddPayrollScreenUiState.Error -> {
            PayrollAddScreenError(
                msg = uiState.errorMassage ?: "Error desconocido",
                onRetry = {}
            )
        }

        is AddPayrollScreenUiState.Ready -> {
            PayrollAddScreenReady(
                onLoadPersons = uiState.persons,
                onLoadEvents = uiState.events,
                onSavePayroll = { payroll -> viewModel.savePayroll(payroll.toEntity()) },
            )
        }
    }
}

@Composable
fun PayrollAddScreenLoading(modifier: Modifier = Modifier) {
    val iconSize = dimensionResource(id = R.dimen.icon_huge_size)
    val areaSize = 94.dp

    Surface(modifier.fillMaxSize()) {
        Box {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(areaSize)
                    .align(Alignment.Center),
                strokeWidth = 8.dp,
                color = MaterialTheme.colorScheme.surface
            )

        }
    }
}

@Composable
fun PayrollAddScreenError(msg: String, onRetry: () -> Unit, modifier: Modifier = Modifier) {
    Surface(modifier = modifier) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize(),
        ) {
            Text(
                text = stringResource(id = R.string.msg_an_error_has_ocurred),
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text =  msg,
                modifier = Modifier.padding(16.dp)
            )
            Button(onClick = onRetry) {
                Text(text = stringResource(id = R.string.label_retry))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectPerson(
    innerPadding: PaddingValues,
    onLoadPersons: List<PersonModel>,
    onPersonSelected: (PersonModel) -> Unit
) {
    val selectedPerson = remember { mutableStateOf("") }
    val persons: List<PersonModel> =  onLoadPersons
    val personsNames: List<String> = persons.map { it.name }

    val selectedPersonModel by remember {
        derivedStateOf {
            persons.find { it.name == selectedPerson.value }
        }
    }

    LaunchedEffect(selectedPersonModel) {
        selectedPersonModel?.let { onPersonSelected(it) }
    }

        Column( modifier = Modifier
            .padding(innerPadding)
            .fillMaxWidth()
        ) {
            if(personsNames.isNotEmpty()) {
                ComboBox(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Personas",
                    field = selectedPerson,
                    items = personsNames,
                    maxlength = 15,
                    selectedIndex = remember { mutableStateOf(0) },
                    expanded = remember { mutableStateOf(false) },
                )


            } else {
                Text(text = stringResource(R.string.lbl_payroll_persons_empty))
            }
        }
}

@Composable
fun SelectEvent(
    innerPadding: PaddingValues,
    onLoadEvents: List<EventModel>,
    onEventSelected: (EventModel) -> Unit
) {
    val selectedEvent = remember { mutableStateOf("") }
    val events: List<EventModel> =  onLoadEvents
    val eventsNames: List<String> = events.map { it.name }

    val selectedEventModel by remember {
        derivedStateOf {
            events.find { it.name == selectedEvent.value }
        }
    }

    LaunchedEffect(selectedEventModel) {
        selectedEventModel?.let { onEventSelected(it) }
    }

    Column( modifier = Modifier
        .padding(innerPadding)
        .fillMaxWidth()
    ) {
        if(eventsNames.isNotEmpty()) {
            ComboBox(
                modifier = Modifier.fillMaxWidth(),
                title = "Eventos",
                field = selectedEvent,
                items = eventsNames,
                maxlength = 15,
                selectedIndex = remember { mutableStateOf(0) },
                expanded = remember { mutableStateOf(false) },
            )
        } else {
            Text(text = stringResource(R.string.lbl_payroll_events_empty))
        }
    }
}

@Composable
fun PayrollForm(
    innerPadding: PaddingValues,
    personSelected: PersonModel,
    eventSelected: EventModel,
    onSavePayroll: (PayrollModel) -> Unit,
){
    var personModel by remember { mutableStateOf(personSelected) }
    var salary by remember { mutableStateOf("") }
    var eventModel by remember { mutableStateOf(eventSelected) }
    var typeActivity by remember { mutableStateOf("") }


        Column( modifier = Modifier
            .padding(innerPadding)
            .fillMaxWidth()
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Text(
                    text = "Persona Seleccionada: ${personModel.name} ${personModel.lastName}",
                    modifier = Modifier
                        .padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Evento Seleccionado: ${eventModel.name}",
                    modifier = Modifier
                        .padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            TextField(
                value = salary,
                onValueChange = { newValue ->
                    salary = newValue
                },
                label = { Text("Salario") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = typeActivity,
                onValueChange = { newValue ->
                    typeActivity = newValue
                },
                label = { Text("Tipo de Actividad") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Button( modifier = Modifier
                .align(Alignment.End),
                onClick = {
                    Log.d(TAG, "onSubmitClick() -> invoked")
                    val payroll = PayrollModel(
                        id = null,
                        event = eventSelected,
                        persons = listOf(
                            PersonSalaryModel(
                                id = null,
                                person = personSelected,
                                salary = salary.toDouble(),
                                typeActivity = typeActivity,
                                payrollId = null
                            )
                        )
                    )
                    onSavePayroll(payroll)
                }
            ) {
                Text("Guardar")
            }
        }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PayrollAddScreenReady(
    onLoadPersons: List<PersonModel>,
    onLoadEvents: List<EventModel>,
    onSavePayroll: (PayrollModel) -> Unit,
) {
    Log.d(TAG, "PayrollPageScreen() -> composed / recomposed")

    val selectedPerson = remember { mutableStateOf<PersonModel?>(null) }
    val selectedEvent = remember { mutableStateOf<EventModel?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(Screens.PayrollAddPageScreen.title) }
            )
        },
    ) { innerPadding ->
        Column( modifier = Modifier
            .padding(innerPadding)
            .fillMaxWidth()
        ) {
            if (selectedPerson.value == null){
                SelectPerson(
                    innerPadding = PaddingValues(16.dp),
                    onLoadPersons = onLoadPersons,
                    onPersonSelected = { person ->
                        selectedPerson.value = person },
                )
            }

            if (selectedPerson.value != null && selectedEvent.value == null){
                SelectEvent(
                    innerPadding = PaddingValues(16.dp),
                    onLoadEvents = onLoadEvents,
                    onEventSelected = {event ->
                        selectedEvent.value = event
                    }
                )
            }

            if (selectedPerson.value != null && selectedEvent.value != null) {
                PayrollForm(
                    innerPadding = PaddingValues(16.dp),
                    personSelected = selectedPerson.value!!,
                    eventSelected = selectedEvent.value!!,
                    onSavePayroll = onSavePayroll
                )
            }
        }
    }
}



@Composable
@Preview(showBackground = true)
fun PayrollAddScreenPreview() {
    KoinApplication( application =  {
        modules(
            module {
                single<LocalRepository> { DummyRepositoryImpl() }
            }
        )
    }) {
        EventManagementTheme {
            val localRepository: LocalRepository = koinInject()

            val events: List<EventEntity>
            val persons: List<PersonEntity>

            runBlocking {
                events = localRepository.getAllEventsFlow().first()
                persons = localRepository.getAllPerson()
            }

            val mockUiState = AddPayrollScreenUiState.Ready(
                events = events.map { it.toModel() },
                persons = persons.map { it.toModel() }
            )

            PayrollAddScreenReady(
                onLoadPersons = mockUiState.persons,
                onLoadEvents = mockUiState.events,
                onSavePayroll = {},
            )
        }

    }

}

private const val TAG = "AddEventPageScreen"