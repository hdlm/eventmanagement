package com.exercises.eventmanagment.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
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
import com.exercises.eventmanagment.R
import com.exercises.eventmanagment.data.database.entities.relations.PayrollWithPersonSalary
import com.exercises.eventmanagment.presentation.domain.EventModel
import com.exercises.eventmanagment.presentation.domain.PayrollModel
import com.exercises.eventmanagment.presentation.domain.PersonSalaryModel
import com.exercises.eventmanagment.ui.theme.DarkColorScheme
import com.exercises.eventmanagment.ui.theme.LightColorScheme
import com.exercises.eventmanagment.ui.theme.YellowGrey

@Composable
fun PayrollItemView(
    payroll: PayrollWithPersonSalary,
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
                Text(text = payroll.payroll.eventId.toString() )
                Spacer(modifier = Modifier.padding(vertical = lineSpacing))
                Text(text = stringResource(R.string.label_payrollPersonsNames), fontWeight = FontWeight.Bold)
                Text(text = payroll.persons.joinToString(", ") { it.personId.toString()})
            }

        }
    }
}


@Composable
@Preview
fun PayrollItemViewPreview() {
//    val payroll = PayrollWithPersonSalary(
//        id = 1,
//        event = EventModel(
//            id = TODO(),
//            name = TODO(),
//            address = TODO(),
//            startEventdate = TODO(),
//            endEventdate = TODO()
//        ),
//        persons = TODO()
//    )
//
//    PayrollItemView(
//        payroll = payroll
//    )
}