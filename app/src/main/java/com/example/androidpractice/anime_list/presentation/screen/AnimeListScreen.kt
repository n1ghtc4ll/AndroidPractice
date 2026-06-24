package com.example.androidpractice.anime_list.presentation.screen

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.app.ComponentActivity
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.androidpractice.anime_list.presentation.viewModel.AnimeListViewModel
import com.example.androidpractice.anime_list.presentation.model.AnimeUiModel
import com.example.androidpractice.MockData
import com.example.androidpractice.anime_list.domain.model.AnimeFilter
import com.example.androidpractice.anime_list.domain.model.AnimeRating
import com.example.androidpractice.anime_list.domain.model.AnimeType
import com.example.androidpractice.anime_list.domain.model.FilterSetting
import com.example.androidpractice.anime_list.presentation.model.AnimeFilterModel
import com.example.androidpractice.anime_list.presentation.model.AnimeListViewState
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AnimeListScreen(viewModel: AnimeListViewModel) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()

    val isBadgeVisible by viewModel.isBadgeVisible.collectAsStateWithLifecycle()

    AnimeListContent(
        state = state.state,
        isBadgeVisible = isBadgeVisible,
        onAnimeClick = viewModel::onAnimeClick,
        onRetryClick = viewModel::onRetryClick,
        onFilterClick = viewModel::onFilterClick
    )
}

@Composable
fun AnimeListContent(
    state: AnimeListViewState.State,
    isBadgeVisible: Boolean = false,
    onAnimeClick: (AnimeUiModel) -> Unit = {},
    onRetryClick: () -> Unit = {},
    onFilterClick: () -> Unit = {}
) {
    Scaffold(
        floatingActionButton = {
            Box {
                FloatingActionButton(onClick = onFilterClick) {
                    Icon(Icons.Default.Build, contentDescription = "Фильтры")
                }

                if (isBadgeVisible) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(2.dp)
                            .size(14.dp)
                            .background(
                                color = Color(0xFFFFA500),
                                shape = androidx.compose.foundation.shape.CircleShape
                            )
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (state) {
                is AnimeListViewState.State.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is AnimeListViewState.State.Error -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = state.error, color = Color.Red)
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = onRetryClick) { Text("Try Again") }
                    }
                }

                is AnimeListViewState.State.Success -> {
                    LazyColumn {
                        state.data.forEach { anime ->
                            item(key = anime.id) {
                                AnimeListItem(anime) { onAnimeClick(anime) }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AnimeListItem(
    anime: AnimeUiModel,
    onAnimeClick: (AnimeUiModel) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 8.dp)
            .clickable(onClick = { onAnimeClick(anime) }),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = anime.title,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = anime.score,
                modifier = Modifier.padding(start = 8.dp),
                style = MaterialTheme.typography.labelLarge
            )
        }
        Text(
            text = anime.type,
            style = MaterialTheme.typography.labelLarge
        )
        Text(
            text = "Episodes: ${anime.episodes}",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Genres: ${anime.genres}",
            style = MaterialTheme.typography.bodyMedium
        )

        HorizontalDivider(modifier = Modifier.padding(top = 4.dp))
    }
}

@Composable
fun AnimeFiltersDialog(viewModel: AnimeListViewModel) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()

    AnimeFiltersDialog(
        state = state,
        onFiltersChange = viewModel::updateListFilters,
        onApply = viewModel::onApplyFilters,
        onDismiss = viewModel::onDismissDialog
    )
}

@Composable
fun AnimeFiltersDialog(
    state: AnimeListViewState,
    onFiltersChange: (AnimeFilterModel) -> Unit,
    onApply: () -> Unit = {},
    onDismiss: () -> Unit = {}
) {

    Dialog(
        onDismissRequest = onDismiss
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surface
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "Фильтры",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                FilterDropdownMenu(
                    title = "Тип релиза",
                    selectedValue = state.dialogFilters.type,
                    entries = AnimeType.entries.toTypedArray(),
                    onValueSelected = { newType -> onFiltersChange(state.dialogFilters.copy(type = newType)) }
                )

                FilterDropdownMenu(
                    title = "Категория",
                    selectedValue = state.dialogFilters.filter,
                    entries = AnimeFilter.entries.toTypedArray(),
                    onValueSelected = { newFilter -> onFiltersChange(state.dialogFilters.copy(filter = newFilter)) }
                )

                FilterDropdownMenu(
                    title = "Возрастной рейтинг",
                    selectedValue = state.dialogFilters.rating,
                    entries = AnimeRating.entries.toTypedArray(),
                    onValueSelected = { newRating -> onFiltersChange(state.dialogFilters.copy(rating = newRating)) }
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Отмена")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = onApply) {
                        Text("Готово")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> FilterDropdownMenu(
    title: String,
    selectedValue: T,
    entries: Array<T>,
    onValueSelected: (T) -> Unit,
    modifier: Modifier = Modifier
) where T : Enum<T>, T : FilterSetting {

    var expanded by remember { mutableStateOf(false) }

    Column(modifier = modifier
        .fillMaxWidth()
        .padding(bottom = 16.dp)) {
        FilterSectionTitle(title)

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it }
        ) {
            OutlinedTextField(
                value = selectedValue.uiName,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                entries.forEach { entry ->
                    DropdownMenuItem(
                        text = { Text(text = entry.uiName) },
                        onClick = {
                            onValueSelected(entry)
                            expanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }
    }
}

@Composable
fun FilterSectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun AnimeListScreenPreview() {
    AnimeListContent(
        AnimeListViewState.State.Success(MockData.getAnimeList())
    )
}