package com.exercises.eventmanagment.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.exercises.eventmanagement.ui.navigation.Screens
import com.exercises.eventmanagment.R
import com.exercises.eventmanagment.presentation.domain.PersonModel
import com.exercises.eventmanagment.presentation.presenters.PersonAddViewModel
import com.exercises.eventmanagment.ui.theme.DarkColorScheme
import com.exercises.eventmanagment.ui.theme.LightColorScheme
import com.exercises.eventmanagment.ui.theme.YellowGrey
import com.exercises.eventmanagment.ui.theme.YellowSurface
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonAddPageScreen(
    navController: NavController,
    innerPadding: PaddingValues,
    viewModel: PersonAddViewModel = koinViewModel(),
    isDarkTheme: Boolean = false
) {

    Log.d(TAG, "PersonPageScreen() -> composed / recomposed")

    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    val onSubmitClick: () -> Unit = {
        Log.d(TAG, "onSubmitClick() -> invoked")
        val person = PersonModel(
            id = null,
            name = name,
            lastName = lastName,
            gender = gender,
            age = age.toInt(),
            phone = phone,
            email = email
        )
        viewModel.savePerson(person)
    }

    val colorScheme = if (isDarkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(colorScheme = colorScheme) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(Screens.PersonAddPageScreen.title) }
            )
        },
        containerColor = YellowSurface
    ) { innerPadding ->
        Column( modifier = Modifier
            .padding(innerPadding)
            .fillMaxWidth()
        ) {
            TextField(
                value = name,
                onValueChange = { newValue ->
                    name = newValue
                },
                label = { Text("Nombre") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = YellowGrey,
                    unfocusedContainerColor = YellowGrey,
                    focusedLabelColor = Color.Black
                )
            )

            TextField(
                value = lastName,
                onValueChange = { newValue ->
                    lastName = newValue
                },
                label = { Text("Apellido") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = YellowGrey,
                    unfocusedContainerColor = YellowGrey,
                    focusedLabelColor = Color.Black
                )
            )

            TextField(
                value = gender,
                onValueChange = { newValue ->
                    gender = newValue
                },
                label = { Text("Género") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = YellowGrey,
                    unfocusedContainerColor = YellowGrey,
                    focusedLabelColor = Color.Black
                )
            )

            TextField(
                value = age,
                onValueChange = { newValue ->
                    age = newValue
                },
                label = { Text("Edad") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = YellowGrey,
                    unfocusedContainerColor = YellowGrey,
                    focusedLabelColor = Color.Black
                )
            )

            TextField(
                value = phone,
                onValueChange = { newValue ->
                    phone = newValue
                },
                label = { Text("Número Telefónico") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = YellowGrey,
                    unfocusedContainerColor = YellowGrey,
                    focusedLabelColor = Color.Black
                )
            )

            TextField(
                value = email,
                onValueChange = { newValue ->
                    email = newValue
                },
                label = { Text("Correo Electrónico") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = YellowGrey,
                    unfocusedContainerColor = YellowGrey,
                    focusedLabelColor = Color.Black
                )
            )

            Button( modifier = Modifier
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

private const val TAG = "PersonPageScreen"
