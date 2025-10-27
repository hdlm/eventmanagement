package com.exercises.eventmanagement.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.exercises.eventmanagement.presentation.presenters.EventAddPageViewModel
import com.exercises.eventmanagement.presentation.presenters.EventPageViewModel
import com.exercises.eventmanagement.presentation.presenters.FurnitureAddPageViewModel
import com.exercises.eventmanagement.presentation.presenters.FurniturePageViewModel
import com.exercises.eventmanagement.presentation.presenters.PayrollAddPageViewModel
import com.exercises.eventmanagement.presentation.presenters.PayrollPageViewModel
import com.exercises.eventmanagement.presentation.presenters.PersonAddViewModel
import com.exercises.eventmanagement.presentation.presenters.PersonPageViewModel
import com.exercises.eventmanagement.ui.EventAddPageScreen
import com.exercises.eventmanagement.ui.EventPageScreen
import com.exercises.eventmanagement.ui.FurnitureAddPageScreen
import com.exercises.eventmanagement.ui.FurniturePageScreen
import com.exercises.eventmanagement.ui.PayrollAddPageScreen
import com.exercises.eventmanagement.ui.PayrollPageScreen
import com.exercises.eventmanagement.ui.PersonAddPageScreen
import com.exercises.eventmanagement.ui.PersonPageScreen

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

        composable(Screens.PayrollPageScreen.route) {
            PayrollPageScreen(
                navController = navController,
                innerPadding = innerPadding,
                viewModel = PayrollPageViewModel()
            )
        }

        composable(Screens.PayrollAddPageScreen.route) {
            PayrollAddPageScreen(
                navController = navController,
                innerPadding = innerPadding,
                viewModel = PayrollAddPageViewModel()
            )
        }


    }



}


