package com.exercises.eventmanagement.ui.components

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
import com.exercises.eventmanagement.R
import com.exercises.eventmanagement.presentation.domain.PersonModel
import com.exercises.eventmanagement.ui.theme.DarkColorScheme
import com.exercises.eventmanagement.ui.theme.LightColorScheme
import com.exercises.eventmanagement.ui.theme.YellowGrey

@Composable
fun PersonItemView(
    person: PersonModel,
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
            Column(
                modifier = Modifier
                    .padding(horizontal = horizontalMargin, vertical = lineSpacing)
                    .fillMaxWidth()
            ) {
                Text(text = "${stringResource(R.string.label_personId)} ${person.id.toString()}", fontWeight = FontWeight.Bold)

                Text(text = stringResource(R.string.label_personName), fontWeight = FontWeight.Bold)
                Text(text = person.name)
                Spacer(modifier = Modifier.padding(vertical = lineSpacing))

                Text(text = stringResource(R.string.label_personLastName), fontWeight = FontWeight.Bold)
                Text(text = person.lastName)
                Spacer(modifier = Modifier.padding(vertical = lineSpacing))

                Text(text = stringResource(R.string.label_personGender), fontWeight = FontWeight.Bold)
                Text(text = person.gender)
                Spacer(modifier = Modifier.padding(vertical = lineSpacing))

                Text(text = stringResource(R.string.label_personAge), fontWeight = FontWeight.Bold)
                Text(text = person.age.toString())
                Spacer(modifier = Modifier.padding(vertical = lineSpacing))

                Text(text = stringResource(R.string.label_personPhone), fontWeight = FontWeight.Bold)
                Text(text = person.phone)
                Spacer(modifier = Modifier.padding(vertical = lineSpacing))

                Text(text = stringResource(R.string.label_personEmail), fontWeight = FontWeight.Bold)
                Text(text = person.email)
            }
        }
    }
}


@Composable
@Preview
fun PersonItemViewPreview() {
    val person = PersonModel(
        id = 1,
        name = "Santiago",
        lastName = "Salas",
        gender = "Masculino",
        age = 18,
        phone = "0412-9847539",
        email = "salassantysk8@gmail.com"
    )

    PersonItemView(
        person = person
    )
}