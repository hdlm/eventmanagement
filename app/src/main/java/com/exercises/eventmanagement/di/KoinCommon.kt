package com.exercises.eventmanagement.di

import com.exercises.eventmanagement.data.database.repositories.LocalRepository
import com.exercises.eventmanagement.data.database.repositories.LocalRepositoryImpl
import com.exercises.eventmanagement.presentation.presenters.AddEventViewModel
import com.exercises.eventmanagement.presentation.presenters.EventPageViewModel
import com.exercises.eventmanagement.presentation.presenters.FurnitureAddPageViewModel
import com.exercises.eventmanagement.presentation.presenters.FurniturePageViewModel
import com.exercises.eventmanagement.presentation.presenters.PayrollAddPageViewModel
import com.exercises.eventmanagement.presentation.presenters.PayrollPageViewModel
import com.exercises.eventmanagement.presentation.presenters.PersonAddViewModel
import com.exercises.eventmanagement.presentation.presenters.PersonPageViewModel
import com.exercises.eventmanagement.presentation.usecase.EventInfoUseCase
import com.exercises.eventmanagement.presentation.usecase.FurnitureInfoUseCase
import com.exercises.eventmanagement.presentation.usecase.PayrollInfoUseCase
import com.exercises.eventmanagement.presentation.usecase.PersonInfoUseCase
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

object Modules {
    val appModule = module {
        factory<LocalRepository> { LocalRepositoryImpl() }
        factory { EventInfoUseCase() }
        factory { FurnitureInfoUseCase() }
        factory { PersonInfoUseCase() }
        factory { PayrollInfoUseCase() }

        viewModel { AddEventViewModel() }
        viewModel { FurnitureAddPageViewModel() }
        viewModel { FurniturePageViewModel() }
        viewModel { PayrollAddPageViewModel() }
        viewModel { PersonAddViewModel() }
        viewModel { EventPageViewModel() }
        viewModel { PersonPageViewModel() }
        viewModel { PayrollPageViewModel() }




    }

    val testModule = module {
        factory<LocalRepository> { LocalRepositoryImpl() }
        factory { EventInfoUseCase() }
        factory { FurnitureInfoUseCase() }
        factory { PersonInfoUseCase() }
    }
}