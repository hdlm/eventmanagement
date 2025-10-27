package com.exercises.eventmanagement.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.exercises.eventmanagement.ui.navigation.Screens
import com.exercises.eventmanagement.R
import com.exercises.eventmanagement.presentation.domain.FurnitureModel
import com.exercises.eventmanagement.presentation.presenters.FurnitureAddPageViewModel
import com.exercises.eventmanagement.ui.theme.DarkColorScheme
import com.exercises.eventmanagement.ui.theme.EventManagementTheme
import com.exercises.eventmanagement.ui.theme.LightColorScheme
import com.exercises.eventmanagement.ui.theme.YellowGrey
import com.exercises.eventmanagement.ui.theme.YellowSurface
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FurnitureAddPageScreen(
    viewModel: FurnitureAddPageViewModel = koinViewModel(),
    isDarkTheme: Boolean = false
) {
    Log.d(TAG, "FurniturePageScreen() -> composed / recomposed")

    var description by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }

    val onSubmitClick: () -> Unit = {
        Log.d(TAG, "onSubmitClick() -> invoked")
        val furniture = FurnitureModel(
            description = description,
            price = price.toDouble()
        )
        viewModel.saveFurniture(furniture)

    }

    val colorScheme = if (isDarkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(colorScheme = colorScheme) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(Screens.FurniturePageScreen.title) }
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
                    value = description,
                    onValueChange = { newValue ->
                        description = newValue
                    },
                    label = { Text("Descripción del Inmueble") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = YellowGrey,
                        unfocusedContainerColor = YellowGrey,
                        focusedLabelColor = Color.Black
                    ),
                    value = price,
                    onValueChange = { newValue ->
                        price = newValue
                    },
                    label = { Text("Precio") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Button(modifier = Modifier
                    .align(Alignment.End),
                    onClick = { onSubmitClick.invoke() }
                ) {
                    Text(text = stringResource(id = R.string.button_guardar), color= Color.Black)
                }
            }
        }

    }
}

@Composable
@Preview(showBackground = true)
fun FurnitureAddPageScreenPreview() {

    EventManagementTheme {
        FurnitureAddPageScreen(
            viewModel = FurnitureAddPageViewModel()
        )
    }
}

private const val TAG = "FurnitureAddPageScreen"