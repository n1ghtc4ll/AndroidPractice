package com.example.androidpractice.anime.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.androidpractice.anime.domain.model.AnimeFilterSettings
import com.example.androidpractice.anime.domain.model.FilterSetting

@Composable
fun AnimeFilterDialog(
    modifier: Modifier = Modifier,
    filters: AnimeFilterSettings,
    onFiltersChanged: (AnimeFilterSettings) -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Surface(
            modifier = modifier,
            shape = RoundedCornerShape(16.dp)
        ) {
            AnimeFilterContent(
                filters = filters,
                onFiltersChanged = {
                    onFiltersChanged(it)
                    onDismiss()
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeFilterContent(
    modifier: Modifier = Modifier,
    filters: AnimeFilterSettings,
    onFiltersChanged: (AnimeFilterSettings) -> Unit,
) {
    var tempFilters by remember { mutableStateOf(filters) }

    Column(
        modifier = modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Фильтры", style = MaterialTheme.typography.titleMedium)

        FilterDropdownMenu(
            entries = FilterSetting.Category.entries.toTypedArray(),
            selectedValue = tempFilters.mainFilter,
            onValueSelected = { newCategory ->
                tempFilters = tempFilters.copy(mainFilter = newCategory)
            }
        )

        FilterDropdownMenu(
            entries = FilterSetting.ReleaseType.entries.toTypedArray(),
            selectedValue = tempFilters.releaseType,
            onValueSelected = { newRelease ->
                tempFilters = tempFilters.copy(releaseType = newRelease)
            }
        )

        FilterDropdownMenu(
            entries = FilterSetting.AgeRating.entries.toTypedArray(),
            selectedValue = tempFilters.ageRating,
            onValueSelected = { newAgeRating ->
                tempFilters = tempFilters.copy(ageRating = newAgeRating)
            }
        )

        Button(onClick = { onFiltersChanged(tempFilters) }) {
            Text(text = "Применить")
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> FilterDropdownMenu(
    modifier: Modifier = Modifier,
    entries: Array<T>,
    selectedValue: T,
    onValueSelected: (T) -> Unit
) where T : Enum<T>, T : FilterSetting {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            modifier = Modifier.menuAnchor(type = ExposedDropdownMenuAnchorType.PrimaryNotEditable),
            readOnly = true,
            value = selectedValue.uiValue,
            onValueChange = { }
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            entries.forEach { entry ->
                DropdownMenuItem(
                    text = { Text(text = entry.uiValue) },
                    onClick = {
                        onValueSelected(entry)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimeFilterContentPreview() {
    AnimeFilterContent(
        filters = AnimeFilterSettings(),
        onFiltersChanged = { }
    )
}