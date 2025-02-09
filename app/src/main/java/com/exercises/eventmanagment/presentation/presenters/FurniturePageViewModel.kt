package com.exercises.eventmanagment.presentation.presenters

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exercises.eventmanagment.presentation.domain.FurnitureModel
import com.exercises.eventmanagment.presentation.usecase.FurnitureInfoUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@OptIn(ExperimentalCoroutinesApi::class)
class FurniturePageViewModel : ViewModel(), KoinComponent {
    private val furnitureInfoUseCase: FurnitureInfoUseCase by inject()

    private val _furnitures = MutableStateFlow<List<FurnitureModel>>(emptyList())

    private val refreshing = MutableStateFlow(false)

    private val _uiState = MutableStateFlow<FurnitureScreenUiState>(FurnitureScreenUiState.Loading)
    val uiState: StateFlow<FurnitureScreenUiState>
        get() = _uiState

    /** this value avoid to show the same error twice */
    var errorShowed: Boolean = false

    init {
        viewModelScope.launch {

            com.exercises.eventmanagment.commons.combine(
                _furnitures.flatMapLatest {
                    furnitureInfoUseCase()
                },
                refreshing
            ) { furnitures,
                refreshing ->

                val uiState = if (refreshing) {
                    Log.d(TAG, "refreshing: $refreshing")
                    FurnitureScreenUiState.Loading
                } else {
                    FurnitureScreenUiState.Ready(
                        furnitures = furnitures
                    )
                }
                uiState

            }.catch { throwable ->
                throwable.printStackTrace()
                _uiState.value = FurnitureScreenUiState.Error(throwable.message)
                Log.e(TAG, "error: ${throwable.message}")
            }.collect {
                _uiState.value = it
            }
        }

    }

    fun refresh(force: Boolean) {
        viewModelScope.launch {
            runCatching {
                refreshing.value = true
                delay(20)
                refreshing.value = false
            }
        }
    }

    companion object {
        const val TAG = "FurniturePageViewModel"
    }

}

sealed interface FurnitureScreenUiState {
    data object Loading: FurnitureScreenUiState

    data class Error(
        val errorMessage: String? = null
    ): FurnitureScreenUiState


    data class Ready(
        val furnitures: List<FurnitureModel> = emptyList()
    ): FurnitureScreenUiState
}