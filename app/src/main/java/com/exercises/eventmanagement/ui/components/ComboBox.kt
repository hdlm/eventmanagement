package com.exercises.eventmanagement.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.exercises.eventmanagement.R
import com.exercises.eventmanagement.ui.theme.EventManagementTheme
import com.exercises.eventmanagement.ui.theme.grayDark

@Composable
fun ComboBox(
    modifier: Modifier,
    title: String,
    field: MutableState<String>,
    items: List<String>,
    maxlength: Int,
    selectedIndex: MutableState<Int>,
    expanded: MutableState<Boolean>,
    enabled: Boolean = true,
) {
    val separation = dimensionResource(id = R.dimen.side_separation_2x)

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = grayDark
    ) {
        val itemText = if (maxlength < 0) items[selectedIndex.value]
        else items[selectedIndex.value].take(maxlength)

        val txt = "$title"

        Text(
            text = txt,
            style = MaterialTheme.typography.bodyLarge,
            modifier = modifier
                .fillMaxWidth()
                .clickable(onClick = { expanded.value = true })
                .background(MaterialTheme.colorScheme.background)
                .padding(start = separation, top = 17.dp, bottom = 17.dp)
        )

        if ( !enabled ) {
            expanded.value = false
        }

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        ) {
            items.forEachIndexed { index, s ->

                DropdownMenuItem(
                    text = {
                        Text(modifier = Modifier.padding(horizontal = 16.dp),
                            text = s, style = MaterialTheme.typography.bodyMedium)
                    },
                    onClick = {
                        field.value = s
                        selectedIndex.value = index
                        expanded.value = false
                    })
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComboBoxPreview() {
    val items = listOf("Manzana", "Banana", "Cereza", "Durazno")
    val selectedIndex = remember { mutableStateOf(0) }
    val expanded = remember { mutableStateOf(true) }
    val field = remember { mutableStateOf("") }

    EventManagementTheme {
        ComboBox(
            modifier = Modifier.padding(16.dp),
            title = "Frutas",
            field = field,
            items = items,
            maxlength = 10,
            selectedIndex = selectedIndex,
            expanded = expanded,
            enabled = true
        )
    }
}
