package com.example.androidpractice.anime.presentation.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.androidpractice.anime.presentation.viewmodel.AnimeMainListViewModel
import com.example.androidpractice.anime.presentation.model.AnimeUiModel
import com.example.androidpractice.MockData
import com.example.androidpractice.anime.presentation.components.AnimeList
import com.example.androidpractice.anime.presentation.components.FilterButton
import com.example.androidpractice.anime.presentation.model.AnimeListState
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AnimeMainListScreen(
    onAnimeClick: (AnimeUiModel) -> Unit
) {
    val viewModel = koinViewModel<AnimeMainListViewModel>()
    val state by viewModel.viewState.collectAsStateWithLifecycle()

    var showFilter by remember { mutableStateOf(false) }

    AnimeMainListContent(
        state = state.state,
        onAnimeClick = onAnimeClick,
        onRetryClick = viewModel::onRetryClick,
        onFilterClick = { showFilter = true }
    )

    if (showFilter) {
        AnimeFilterDialog(
            filters = state.filters,
            onFiltersChanged = viewModel::updateFilters,
            onDismiss = { showFilter = false },
        )
    }
}

@Composable
fun AnimeMainListContent(
    state: AnimeListState,
    onAnimeClick: (AnimeUiModel) -> Unit = {},
    onRetryClick: () -> Unit = {},
    onFilterClick: () -> Unit = {}
) {
    Scaffold(
        floatingActionButton = { FilterButton(onClick = onFilterClick) }
    ) { paddingValues ->
        AnimeList(
            modifier = Modifier.padding(paddingValues),
            state = state,
            emptyMessage = "По запросу ничего не найдено",
            onAnimeClick = onAnimeClick,
            onRetryClick = onRetryClick
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AnimeMainListScreenPreview() {
    AnimeMainListContent(
        AnimeListState.Success(MockData.getAnimeList())
    )
}