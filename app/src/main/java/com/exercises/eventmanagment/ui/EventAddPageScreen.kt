package com.exercises.eventmanagment.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.exercises.eventmanagement.presentation.presenters.EventAddPageViewModel
import com.exercises.eventmanagement.ui.navigation.Screens
import com.exercises.eventmanagment.R
import com.exercises.eventmanagment.presentation.domain.EventModel
import com.exercises.eventmanagment.ui.theme.DarkColorScheme
import com.exercises.eventmanagment.ui.theme.EventManagementTheme
import com.exercises.eventmanagment.ui.theme.LightColorScheme
import com.exercises.eventmanagment.ui.theme.YellowGrey
import com.exercises.eventmanagment.ui.theme.YellowSurface
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventAddPageScreen(
    navController: NavController,
    innerPadding: PaddingValues,
    viewModel: EventAddPageViewModel = koinViewModel(),
    isDarkTheme: Boolean = false
) {
    Log.d(TAG, "AddEventPageScreen() -> composed / recomposed")

    val events = viewModel.events.collectAsStateWithLifecycle(emptyList())
    val items = events.value

    val colorScheme = if (isDarkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(colorScheme = colorScheme) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(items) { item ->
                Text(text = item.name)
            }

        }


        var eventName by remember { mutableStateOf("") }
        var eventAddress by remember { mutableStateOf("") }
        var startEventDate by remember { mutableStateOf("") }
        var endEventDate by remember { mutableStateOf("") }

        val onSubmitClick: () -> Unit = {
            Log.d(TAG, "onSubmitClick() -> invoked")
            val event = EventModel(
                name = eventName,
                address = eventAddress,
                startEventdate = startEventDate,
                endEventdate = endEventDate,

                )
            viewModel.saveEvent(event)

        }

        val onButtonClick: () -> Unit = {
            Log.d(TAG, "onButtonClick() -> invoked")

        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(Screens.EventAddPageScreen.title) },
                )
            },
            containerColor = YellowSurface,
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxWidth()
            ) {
                TextField(
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = YellowGrey,
                        unfocusedContainerColor = YellowGrey,
                        focusedLabelColor = Color.Black
                    ),
                    value = eventName,
                    onValueChange = { newValue ->
                        eventName = newValue
                    },
                    label = { Text("Nombre del Evento") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                )

                TextField(
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = YellowGrey,
                        unfocusedContainerColor = YellowGrey,
                        focusedLabelColor = Color.Black
                    ),
                    value = eventAddress,
                    onValueChange = { newValue ->
                        eventAddress = newValue
                    },
                    label = { Text("Lugar o Direeción") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = YellowGrey,
                        unfocusedContainerColor = YellowGrey,
                        focusedLabelColor = Color.Black
                    ),
                    value = startEventDate,
                    onValueChange = { newValue ->
                        startEventDate = newValue
                    },
                    label = { Text("Fecha de incio") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                )

                TextField(
                    value = endEventDate,
                    onValueChange = { newValue ->
                        endEventDate = newValue
                    },
                    label = { Text("Fecha de finalización") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = YellowGrey,
                        unfocusedContainerColor = YellowGrey,
                        focusedLabelColor = Color.Black
                    )
                )

                Button(modifier = Modifier
                    .align(Alignment.End)
                    .padding(all = 5.dp),
                    onClick = { onSubmitClick.invoke() }
                ) {
                    Text(text = stringResource(id = R.string.button_guardar), color = Color.Black)
                }
            }
        }

    }
}

@Composable
 @Preview(showBackground = true)
fun AddEventScreenPreview() {
    val navController = NavController(LocalContext.current)
    val innerPadding = PaddingValues()

    EventManagementTheme {
        EventAddPageScreen(
            navController = navController,
            innerPadding = innerPadding,
            viewModel = EventAddPageViewModel()
        )
    }
}

private const val TAG = "AddEventPageScreen"
