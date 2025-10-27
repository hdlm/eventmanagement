package com.exercises.eventmanagement.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.exercises.eventmanagement.R
import com.exercises.eventmanagement.data.database.entities.EventEntity
import com.exercises.eventmanagement.data.database.entities.PersonEntity
import com.exercises.eventmanagement.data.database.entities.relations.PayrollWithPersonSalary
import com.exercises.eventmanagement.data.database.repositories.LocalRepository
import com.exercises.eventmanagement.data.mapper.toModel
import com.exercises.eventmanagement.data.repositories.DummyRepositoryImpl
import com.exercises.eventmanagement.presentation.domain.EventModel
import com.exercises.eventmanagement.presentation.domain.PayrollModel
import com.exercises.eventmanagement.presentation.domain.PersonModel
import com.exercises.eventmanagement.presentation.domain.PersonSalaryModel
import com.exercises.eventmanagement.presentation.presenters.PayrollScreenUiState
import com.exercises.eventmanagement.ui.PayrollPageScreenReady
import com.exercises.eventmanagement.ui.theme.DarkColorScheme
import com.exercises.eventmanagement.ui.theme.EventManagementTheme
import com.exercises.eventmanagement.ui.theme.LightColorScheme
import com.exercises.eventmanagement.ui.theme.YellowGrey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject
import org.koin.dsl.module

@Composable
fun PayrollItemView(
    payroll: PayrollWithPersonSalary,
    event: EventModel,
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean = false
) {
    val horizontalMargin = dimensionResource(R.dimen.horizontal_margin)
    val lineSpacing = dimensionResource(R.dimen.line_spacing)

    val colorScheme = if (isDarkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(colorScheme = colorScheme) {
        Card( modifier = modifier,
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
            colors = CardDefaults.cardColors(
                containerColor = YellowGrey,
                contentColor = Color.Black
            )
        ) {
            Column(modifier = Modifier.padding(horizontal = horizontalMargin, vertical = lineSpacing)
                .fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.label_payrollEventName), fontWeight = FontWeight.Bold)
                Text(text = event.name)
                Spacer(modifier = Modifier.padding(vertical = lineSpacing))
                Text(text = stringResource(R.string.label_payrollPersonsNames), fontWeight = FontWeight.Bold)
                Text(text = payroll.persons.size.toString())
            }

        }
    }
}


@Composable
@Preview
fun PayrollItemViewPreview() {
    KoinApplication( application =  {
        modules(
            module {
                single<LocalRepository> { DummyRepositoryImpl() }
            }
        )
    }) {
        EventManagementTheme {
            val localRepository: LocalRepository = koinInject()

            val payroll: PayrollWithPersonSalary
            val event: EventEntity

            runBlocking {
                payroll = localRepository.getAllPayrollFlow().first()[0]
                event = localRepository.getEventById(1)
            }

           PayrollItemView(
               payroll = payroll,
               event = event.toModel(),
           )
        }

    }
}