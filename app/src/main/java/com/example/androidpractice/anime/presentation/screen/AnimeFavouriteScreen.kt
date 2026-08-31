package com.example.androidpractice.anime.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.androidpractice.anime.presentation.model.AnimeFavouriteViewState
import com.example.androidpractice.anime.presentation.model.AnimeListState
import com.example.androidpractice.anime.presentation.model.AnimeUiModel
import com.example.androidpractice.anime.presentation.viewModel.AnimeFavouriteViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AnimeFavouriteScreen(
    onAnimeClick: (AnimeUiModel) -> Unit
) {
    val viewModel = koinViewModel<AnimeFavouriteViewModel>()
    val state by viewModel.viewState.collectAsStateWithLifecycle()

    AnimeFavouriteContent(
        state = state.state,
        onAnimeClick = onAnimeClick,
        onRetryClick = viewModel::onRetryClick
    )
}

@Composable
fun AnimeFavouriteContent(
    state: AnimeListState,
    onAnimeClick: (AnimeUiModel) -> Unit = {},
    onRetryClick: () -> Unit = {}
) {
    when (state) {
        is AnimeListState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is AnimeListState.Empty -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Вы пока ничего не добавили в избранное")
            }
        }
        is AnimeListState.Error -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = state.error, color = Color.Red)
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = onRetryClick) { Text("Повторить") }
            }
        }
        is AnimeListState.Success -> {
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

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AnimeFavouriteContentPreview() {
    AnimeFavouriteContent(
        AnimeListState.Empty
    )
}