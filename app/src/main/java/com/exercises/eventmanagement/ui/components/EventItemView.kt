package com.exercises.eventmanagment.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ColorScheme
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
import com.exercises.eventmanagment.presentation.domain.EventModel
import com.exercises.eventmanagment.presentation.domain.FurnitureModel
import com.exercises.eventmanagment.ui.theme.DarkColorScheme
import com.exercises.eventmanagment.ui.theme.LightColorScheme
import com.exercises.eventmanagment.ui.theme.OrangeGray
import com.exercises.eventmanagment.ui.theme.YellowGrey

@Composable
fun EventItemView(
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
            Text(text = stringResource(R.string.label_eventName), fontWeight = FontWeight.Bold)
            Text(text = event.name)
            Spacer(modifier = Modifier.padding(vertical = lineSpacing))
            Text(text = stringResource(R.string.label_eventDate), fontWeight = FontWeight.Bold)
            Text(text = event.startEventdate)
        }

    }
}
}


@Composable
@Preview
fun EventItemViewPreview() {
    val event = EventModel(
        id = 1,
        name = "Sofa Cama Matrimonial",
        address = "Sin Especificar",
        startEventdate = "01/01/2025",
        endEventdate = "Sin Especificar"
    )

    EventItemView(
        event = event
    )
}