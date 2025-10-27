package com.exercises.eventmanagement.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.exercises.eventmanagement.ui.navigation.Screens
import com.exercises.eventmanagement.ui.AddEventScreen
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
            PersonAddPageScreen()
        }

        composable(Screens.PersonPageScreen.route) {
            PersonPageScreen(
                navController = navController,
                innerPadding = innerPadding,
            )
        }

        composable(Screens.EventAddPageScreen.route) {
            AddEventScreen()
        }

        composable(Screens.EventPageScreen.route) {
            EventPageScreen(
                navController = navController,
                innerPadding = innerPadding,
            )
        }

        composable(Screens.FurniturePageScreen.route) {
            FurniturePageScreen(
                navController = navController,
                innerPadding = innerPadding,
            )
        }

        composable(Screens.FurnitureAddPageScreen.route) {
            FurnitureAddPageScreen()
        }

        composable(Screens.PayrollPageScreen.route) {
            PayrollPageScreen(
                navController = navController,
                innerPadding = innerPadding,
            )
        }

        composable(Screens.PayrollAddPageScreen.route) {
            PayrollAddPageScreen()
        }
    }
}


