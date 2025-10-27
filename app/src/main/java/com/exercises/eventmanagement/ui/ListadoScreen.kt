package com.exercises.eventmanagement.ui

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.exercises.eventmanagement.presentation.presenters.ListadoViewModel

@Composable
fun ListadoScreen(
    navController: NavHostController,
    innerPadding: PaddingValues,
    viewModel: ListadoViewModel
) {
    Log.i(TAG, "ListadoScreen() -> compose /re-compose")

    val muebles by viewModel.muebles.collectAsStateWithLifecycle()



}

private const val TAG = "ListadoScreen"