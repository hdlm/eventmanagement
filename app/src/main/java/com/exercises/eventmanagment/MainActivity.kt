package com.exercises.eventmanagment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.exercises.eventmanagement.commons.CommonValues
import com.exercises.eventmanagement.di.Modules.appModule
import com.exercises.eventmanagement.ui.navigation.AppNavigation
import com.exercises.eventmanagement.ui.navigation.Screens
import com.exercises.eventmanagment.ui.theme.DarkColorScheme
import com.exercises.eventmanagment.ui.theme.EventManagementTheme
import com.exercises.eventmanagment.ui.theme.LightColorScheme
import com.exercises.eventmanagment.ui.theme.Yellow
import com.exercises.eventmanagment.ui.theme.YellowSurface
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.error.ApplicationAlreadyStartedException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        CommonValues.context = applicationContext

        // declaracion de Koin en la app
        try {
            startKoin {
                androidContext(this@MainActivity)
                androidLogger()
                modules(appModule)
            }
        } catch (ex: ApplicationAlreadyStartedException) {
            // ignore
        }

        setContent {
            EventManagementTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EventManagementTheme {
        Greeting("Android")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    isDarkTheme: Boolean = false
) {
    val navController = rememberNavController()

    // elementos de navegacion que estaran en el bottom Bar del Scaffold
    val navigationItems = listOf(
        Screens.EventPageScreen,
        Screens.FurniturePageScreen,
        Screens.PersonPageScreen,
        Screens.PayrollAddPageScreen
    )

    val currentRoute = currentRoute(navController)

    val colorScheme = if (isDarkTheme) DarkColorScheme else LightColorScheme

        Scaffold(
            /*topBar = {
            TopAppBar(
                title = {
                    Text("Gestor de Eventos")
                }
            )
        },*/
            bottomBar = {
                currentRoute?.let {
                    BottomAppBar(
//                    containerColor = MaterialTheme.colorScheme.primaryContainer,
//                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    ) {
                        BottomNavigationBar(navController = navController, items = navigationItems)
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            text = "Bottom App Bar"
                        )
                    }
                }
            },
            floatingActionButton = {
                // omitir el  floating Action button
            },
            content = { innerPadding ->
                AppNavigation(
                    navController = navController,
                    startDest = Screens.EventPageScreen,
                    innerPadding = innerPadding,
                )
            }
        )

    }


@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    items: List<Screens>,
) {

    val currentRoute = currentRoute(navController)

    NavigationBar(
        containerColor = YellowSurface,
        contentColor = Yellow,
    ) {
        items.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(imageVector = screen.icon, contentDescription = screen.title) },
                label = { BottomNavigationBarText(selected = currentRoute == screen.route, label = screen.title ) },
                selected = currentRoute == screen.route,
                onClick = {
                    if (screen.route == Screens.PayrollAddPageScreen.route) {
                        val destination = Screens.PayrollAddPageScreen.route
                        navController.navigate(destination)
                   }
                    if (screen.route == Screens.PersonPageScreen.route) {
                        val destination = Screens.PersonPageScreen.route
                        navController.navigate(destination)
                    }
                    else if (screen.route == Screens.EventPageScreen.route) {
                        navController.navigate(screen.route) {
                            val destination = Screens.EventPageScreen.route
                            navController.navigate(destination)
                        }
                    }
                    else if (screen.route == Screens.FurniturePageScreen.route) {
                        navController.navigate(screen.route) {
                            val destination = Screens.FurniturePageScreen.route
                            navController.navigate(destination)
                        }
                    }


                },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Yellow,
                    unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedTextColor = MaterialTheme.colorScheme.onSecondary,
                )
            )
        }
    }
}



@Composable
private fun BottomNavigationBarText(
    selected: Boolean,
    label: String,
) {
    if(selected) {
        Text(label, fontSize = 11.sp, fontWeight = FontWeight.Normal, color = Color.Black)
    } else {
        Text(label, fontSize = 11.sp, fontWeight = FontWeight.Normal, color = Color.Black)
    }
}


@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

