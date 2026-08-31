package com.example.androidpractice.anime.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.androidpractice.anime.presentation.viewmodel.AnimeListViewModel
import com.example.androidpractice.anime.presentation.model.AnimeUiModel
import com.example.androidpractice.MockData
import com.example.androidpractice.anime.presentation.components.AnimeList
import com.example.androidpractice.anime.presentation.model.AnimeListState
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AnimeMainListScreen(
    onAnimeClick: (AnimeUiModel) -> Unit
) {
    val viewModel = koinViewModel<AnimeListViewModel>()
    val state by viewModel.viewState.collectAsStateWithLifecycle()

    AnimeMainListContent(
        state.state,
        onAnimeClick,
        viewModel::onRetryClick
    )
}

@Composable
fun AnimeMainListContent(
    state: AnimeListState,
    onAnimeClick: (AnimeUiModel) -> Unit = {},
    onRetryClick: () -> Unit = {}
) {
    AnimeList(
        state = state,
        emptyMessage = "По запросу ничего не найдено",
        onAnimeClick = onAnimeClick,
        onRetryClick = onRetryClick
    )
}

@Preview(showBackground = true)
@Composable
fun AnimeMainListScreenPreview() {
    AnimeMainListContent(
        AnimeListState.Success(MockData.getAnimeList())
    )
}