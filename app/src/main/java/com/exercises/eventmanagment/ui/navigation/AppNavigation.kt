package com.exercises.eventmanagement.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.exercises.eventmanagement.presentation.presenters.EventAddPageViewModel
import com.exercises.eventmanagment.presentation.presenters.EventPageViewModel
import com.exercises.eventmanagment.presentation.presenters.FurnitureAddPageViewModel
import com.exercises.eventmanagment.presentation.presenters.FurniturePageViewModel
import com.exercises.eventmanagment.presentation.presenters.PayrolPageViewModel
import com.exercises.eventmanagment.presentation.presenters.PersonAddViewModel
import com.exercises.eventmanagment.presentation.presenters.PersonPageViewModel
import com.exercises.eventmanagment.ui.EventAddPageScreen
import com.exercises.eventmanagment.ui.EventPageScreen
import com.exercises.eventmanagment.ui.FurnitureAddPageScreen
import com.exercises.eventmanagment.ui.FurniturePageScreen
import com.exercises.eventmanagment.ui.PayrollAddPageScreen
import com.exercises.eventmanagment.ui.PersonAddPageScreen
import com.exercises.eventmanagment.ui.PersonPageScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    startDest: Screens,
    innerPadding: PaddingValues,
) {

    NavHost(navController = navController, startDestination = startDest.route ) {
        composable(Screens.PersonAddPageScreen.route) {
            PersonAddPageScreen(
                navController = navController,
                innerPadding = innerPadding,
                viewModel = PersonAddViewModel()
            )

        }

        composable(Screens.PersonPageScreen.route) {
            PersonPageScreen(
                navController = navController,
                innerPadding = innerPadding,
                viewModel = PersonPageViewModel()
            )

        }

        composable(Screens.EventAddPageScreen.route) {
            EventAddPageScreen(
                navController = navController,
                innerPadding = innerPadding,
                viewModel = EventAddPageViewModel()
            )
        }

        composable(Screens.EventPageScreen.route) {
            EventPageScreen(
                navController = navController,
                innerPadding = innerPadding,
                viewModel = EventPageViewModel()
            )
        }

        composable(Screens.FurniturePageScreen.route) {
            FurniturePageScreen(
                navController = navController,
                innerPadding = innerPadding,
                viewModel = FurniturePageViewModel()
            )
        }

        composable(Screens.FurnitureAddPageScreen.route) {
            FurnitureAddPageScreen(
                navController = navController,
                innerPadding = innerPadding,
                viewModel = FurnitureAddPageViewModel()
            )
        }

        composable(Screens.PayrollAddPageScreen.route) {
            PayrollAddPageScreen(
                navController = navController,
                innerPadding = innerPadding,
                viewModel = PayrolPageViewModel()
            )
        }


    }



}


