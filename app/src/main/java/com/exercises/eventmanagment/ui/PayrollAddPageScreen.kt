package com.exercises.eventmanagment.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.exercises.eventmanagement.ui.navigation.Screens
import com.exercises.eventmanagment.presentation.domain.PayrollModel
import com.exercises.eventmanagment.presentation.presenters.PayrollAddPageViewModel
import com.exercises.eventmanagment.ui.theme.EventManagementTheme
import org.koin.androidx.compose.koinViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.exercises.eventmanagment.R
import com.exercises.eventmanagment.ui.theme.grayDark


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PayrollAddPageScreen(
    navController: NavController,
    innerPadding: PaddingValues,
    viewModel: PayrollAddPageViewModel = koinViewModel()
) {
    Log.d(TAG, "PayrollPageScreen() -> composed / recomposed")

    val onSubmitClick: (PayrollModel) -> Unit = { payroll ->
        Log.d(TAG, "onSubmitClick() -> invoked")
//        viewModel.savePayroll(payroll)
    }

    var personModel by remember { mutableStateOf("") }
    var salary by remember { mutableStateOf("") }
    var eventModel by remember { mutableStateOf("") }
    var typeActivity by remember { mutableStateOf("") }
    val selectedPerson = remember { mutableStateOf("") }
    val selectedEvent = remember { mutableStateOf("") }
    var persons : Array<String> = arrayOf()

    val onLoadPersons: (Array<String>) -> Unit = { nameOfPersons ->
        Log.d(TAG, "onLoadPersons() -> invoked")
        persons = nameOfPersons
    }

//    viewModel.getPersons(onLoadPersons)


//    runBlocking {
//        Log.d(TAG, "runBlocking() -> invoked")
//        viewModel.mycoroutine()
//
//    }

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
            /*
             ComboBox(modifier: Modifier,
                         field: MutableState<String>,
                         items: Array<String>,
                         maxlength: Int,
                         selectedIndex: MutableState<Int>,
                         expanded: MutableState<Boolean>,
                         enabled: Boolean = true,
             */
            if(persons.isNotEmpty()) {
                ComboBox(
                    modifier = Modifier.fillMaxWidth(),
                    field = selectedPerson,
                    items =  persons,
                    maxlength = 15,
                    selectedIndex = remember { mutableStateOf(0) },
                    expanded = remember { mutableStateOf(false) },
                )
            } else {
                Text(text = stringResource(R.string.lbl_payroll_empty))
            }

            TextField(
                value = personModel,
                onValueChange = { newValue ->
                    personModel = newValue
                },
                label = { Text("Personal") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

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
                value = eventModel,
                onValueChange = { newValue ->
                    eventModel = newValue
                },
                label = { Text("Evento") },
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
                //TODO pasar el objeto a guardar
                onClick = {
//                    val payroll = PayrollModel(
//                        id = null,
//                        person =
//                        salary = salary,
//                        event = eventModel,
//                        typeActivity = TODO()
//                    )
//                    onSubmitClick.invoke()
                    Log.d(TAG, "onSubmitClick() -> invoked")
                }
            ) {
                Text("Guardar")
            }
        }
    }

}

@Composable
private fun ComboBox(modifier: Modifier,
                     field: MutableState<String>,
                     items: Array<String>,
                     maxlength: Int,
                     selectedIndex: MutableState<Int>,
                     expanded: MutableState<Boolean>,
                     enabled: Boolean = true,
) {
    val separation = dimensionResource(id = R.dimen.side_separation_2x)

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = grayDark
    ) {
        var txt = if( maxlength < 0) items[selectedIndex.value] else items[selectedIndex.value].substring(0,maxlength)
        txt += " - "
        field.value = txt.trim().split("-").first()
        Text(
            text = txt,
            style = MaterialTheme.typography.bodyLarge,
            modifier = modifier
                .fillMaxWidth()
                .clickable(onClick = { expanded.value = true })
                .background(MaterialTheme.colorScheme.background)
                .padding(start = separation, top = 17.dp, bottom = 17.dp)
        )

        if ( !enabled ) {
            expanded.value = false
        }

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        ) {
            items.forEachIndexed { index, s ->

                DropdownMenuItem(
                    text = {
                        Text(modifier = Modifier.padding(horizontal = 16.dp),
                            text = s, style = MaterialTheme.typography.bodyMedium)
                    },
                    onClick = {
//                        field.value = s
                        selectedIndex.value = index
                        expanded.value = false
                    })
            }

        }
    }
}




@Composable
@Preview(showBackground = true)
fun PayrollPageScreenPreview() {
    val navController = NavController(LocalContext.current)
    val innerPadding = PaddingValues()
    val viewModel = PayrollAddPageViewModel()

    EventManagementTheme {
        PayrollAddPageScreen(
            navController = navController,
            innerPadding = innerPadding,
            viewModel = viewModel
        )
    }
}




private const val TAG = "AddEventPageScreen"
