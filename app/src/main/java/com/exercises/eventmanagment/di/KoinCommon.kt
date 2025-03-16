package com.exercises.eventmanagement.di

import com.exercises.eventmanagement.data.database.repositories.LocalRepository
import com.exercises.eventmanagement.data.database.repositories.LocalRepositoryImpl
import com.exercises.eventmanagement.presentation.presenters.EventAddPageViewModel
import com.exercises.eventmanagment.presentation.presenters.FurnitureAddPageViewModel
import com.exercises.eventmanagment.presentation.presenters.FurniturePageViewModel
import com.exercises.eventmanagment.presentation.presenters.PayrollAddPageViewModel
import com.exercises.eventmanagment.presentation.presenters.PersonAddViewModel
import com.exercises.eventmanagment.presentation.usecase.EventInfoUseCase
import com.exercises.eventmanagment.presentation.usecase.FurnitureInfoUseCase
import com.exercises.eventmanagment.presentation.usecase.PersonInfoUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Modules {
    val appModule = module {
        factory<LocalRepository> { LocalRepositoryImpl() }
        factory { EventInfoUseCase() }
        factory { FurnitureInfoUseCase() }
        factory { PersonInfoUseCase() }
        viewModel { EventAddPageViewModel() }
        viewModel { FurnitureAddPageViewModel() }
        viewModel { FurniturePageViewModel() }
        viewModel { PayrollAddPageViewModel() }
        viewModel { PersonAddViewModel() }


    }
}