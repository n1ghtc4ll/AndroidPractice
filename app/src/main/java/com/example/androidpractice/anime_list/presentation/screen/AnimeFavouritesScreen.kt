package com.example.androidpractice.anime_list.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.androidpractice.anime_list.presentation.model.AnimeUiModel
import com.example.androidpractice.anime_list.presentation.viewModel.AnimeFavouritesViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AnimeFavouritesScreen(
    viewModel: AnimeFavouritesViewModel = koinViewModel(),
    onAnimeClick: (AnimeUiModel) -> Unit
) {
    val favourites by viewModel.favouritesList.collectAsState()

    AnimeFavouritesContent(
        favourites = favourites,
        onAnimeClick = onAnimeClick
    )
}

@Composable
fun AnimeFavouritesContent(
    favourites: List<AnimeUiModel>,
    onAnimeClick: (AnimeUiModel) -> Unit
) {
    if (favourites.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "В избранном пока пусто.\nДобавьте что-нибудь!",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(favourites, key = { it.id }) { anime ->
                AnimeListItem(
                    anime = anime,
                    onAnimeClick = onAnimeClick
                )
            }
        }
    }
}