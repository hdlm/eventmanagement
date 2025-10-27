package com.exercises.eventmanagement.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.filled.AddToHomeScreen
import androidx.compose.material.icons.automirrored.filled.EventNote
import androidx.compose.material.icons.automirrored.filled.ShowChart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object EventAddPageScreen: Screens(route = "EventAddPageScreen", title = "Datos del Evento" ,  Icons.AutoMirrored.Filled.AddToHomeScreen)
    object EventPageScreen: Screens(route = "EventPageScreen", title = "Eventos" ,  Icons.AutoMirrored.Filled.AddToHomeScreen)
    object FurniturePageScreen: Screens(route= "FurniturePageScreen", title = "Inmobiliario", icon = Icons.AutoMirrored.Filled.ShowChart)
    object FurnitureAddPageScreen: Screens(route= "FurnitureAddPageScreen", title = "Inmobiliario", icon = Icons.AutoMirrored.Filled.ShowChart)
    object PayrollPageScreen: Screens(route = "PayrollPageScreen", title = "Nómina" ,  Icons.AutoMirrored.Filled.EventNote)
    object PayrollAddPageScreen: Screens(route = "PayrollAddPageScreen", title = "Nómina" ,  Icons.AutoMirrored.Filled.EventNote)
    object PersonPageScreen: Screens(route = "PersonPageScreen", title = "Datos Personales" ,  Icons.AutoMirrored.Filled.Chat)
    object PersonAddPageScreen: Screens(route = "PersonAddPageScreen", title = "Datos Personales" ,  Icons.AutoMirrored.Filled.Chat)
}