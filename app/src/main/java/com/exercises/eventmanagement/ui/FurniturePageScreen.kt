package com.exercises.eventmanagment.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.exercises.eventmanagement.ui.navigation.Screens
import com.exercises.eventmanagment.R
import com.exercises.eventmanagment.presentation.presenters.FurniturePageViewModel
import com.exercises.eventmanagment.presentation.presenters.FurnitureScreenUiState
import com.exercises.eventmanagment.ui.components.FurnitureItemView
import com.exercises.eventmanagment.ui.theme.DarkColorScheme
import com.exercises.eventmanagment.ui.theme.EventManagementTheme
import com.exercises.eventmanagment.ui.theme.LightColorScheme
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FurniturePageScreen(
    navController: NavController,
    innerPadding: PaddingValues,
    viewModel: FurniturePageViewModel = koinViewModel(),
    isDarkTheme: Boolean = false
) {
    val colorScheme = if (isDarkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(colorScheme = colorScheme) {
        Log.d(TAG, "FurniturePageScreen() -> composed / recomposed")

        val furnitureScreenUiState by viewModel.uiState.collectAsStateWithLifecycle()
        when (val uiState = furnitureScreenUiState) {
            is FurnitureScreenUiState.Loading ->
                FurniturePageScreenLoading()

            is FurnitureScreenUiState.Error -> {
                FurniturePageScreenError(
                    msg = uiState.errorMessage!!,
                    onRetry = {}
                )
            }

            is FurnitureScreenUiState.Ready -> {
                FurniturePageScreenReady(
                    navController = navController,
                    uiState = uiState,
                    innerPadding = innerPadding
                )
            }
        }
    }
}


@Composable
fun FurniturePageScreenLoading(modifier: Modifier = Modifier) {
    val iconSize = dimensionResource(id = R.dimen.icon_huge_size)
    val areaSize = 94.dp

    Surface(modifier.fillMaxSize()) {
        Box {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(areaSize)
                    .align(Alignment.Center),
                strokeWidth = 8.dp,
                color = MaterialTheme.colorScheme.primary
            )

        }
    }
}


@Composable
fun FurniturePageScreenError(msg: String, onRetry: () -> Unit, modifier: Modifier = Modifier) {
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
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FurniturePageScreenReady(
    navController: NavController,
    uiState: FurnitureScreenUiState.Ready,
    innerPadding: PaddingValues,
) {
    val topBarHeight = dimensionResource(id = R.dimen.top_bar_height)
    val horizontalMargin = dimensionResource(id = R.dimen.horizontal_margin)

    val onChangeScreenClick: () -> Unit = {
        Log.d(TAG, "onChangeScreenClick() -> invoked")
        val changeScreen = Screens.FurnitureAddPageScreen.route
        navController.navigate(changeScreen)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(Screens.FurniturePageScreen.title) }
            )
        },
        floatingActionButton = {
            Button(modifier = Modifier
                .padding(innerPadding),
                onClick = onChangeScreenClick )
            {
                Text("+", color = Color.Black)
            }
        }
    ) {
        Surface( modifier = Modifier.padding(top = topBarHeight )
            .fillMaxSize()
        ) {
            LazyColumn( modifier = Modifier.padding(innerPadding)) {
                items(uiState.furnitures) { furniture ->
                    FurnitureItemView(modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = horizontalMargin),
                        furniture = furniture
                    )
                }

                item {
                    //TODO aqui va el boton de agregar
                }

            }


        }
    }
}



@Composable
@Preview(showBackground = true)
fun FurniturePageScreenPreview() {
    val navController = NavController(LocalContext.current)
    val innerPadding = PaddingValues()

    EventManagementTheme {
        FurniturePageScreen(
            navController = navController,
            innerPadding = innerPadding,
            viewModel = FurniturePageViewModel()
        )
    }
}

private const val TAG = "FurniturePageScreen"