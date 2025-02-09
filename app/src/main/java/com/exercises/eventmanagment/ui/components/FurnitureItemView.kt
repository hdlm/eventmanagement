package com.exercises.eventmanagment.ui.components

import androidx.compose.foundation.BorderStroke
import com.exercises.eventmanagment.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.exercises.eventmanagment.presentation.domain.FurnitureModel


@Composable
fun FurnitureItemView(
    furniture: FurnitureModel,
    modifier: Modifier = Modifier
) {
    val horizontalMargin = dimensionResource(R.dimen.horizontal_margin)
    val lineSpacing = dimensionResource(R.dimen.line_spacing)

    Card( modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
    ) {
        Column(modifier = Modifier.padding(horizontal = horizontalMargin, vertical = lineSpacing)
            .fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.label_description), fontWeight = FontWeight.Bold)
            Text(text = furniture.description)
            Spacer(modifier = Modifier.padding(vertical = lineSpacing))
            Text(text = stringResource(R.string.label_price), fontWeight = FontWeight.Bold)
            Text(text = furniture.price.toString())
        }

    }

}


@Composable
@Preview
fun FurnitureItemViewPreview() {
    val furniture = FurnitureModel(
        id = 1,
        description = "Sofa Cama Matrimonial",
        price = 150.05
    )

    FurnitureItemView(
        furniture = furniture
    )
}