package com.example.androidpractice.anime.presentation.components

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.androidpractice.anime.presentation.model.AnimeListState
import com.example.androidpractice.anime.presentation.model.AnimeUiModel

@Composable
fun AnimeList(
    modifier: Modifier = Modifier,
    state: AnimeListState,
    emptyMessage: String,
    onAnimeClick: (AnimeUiModel) -> Unit,
    onRetryClick: () -> Unit
) {
    when (state) {
        is AnimeListState.Loading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is AnimeListState.Empty -> {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = emptyMessage)
            }
        }

        is AnimeListState.Error -> {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = state.error, color = Color.Red)
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = onRetryClick) { Text("Повторить") }
            }
        }

        is AnimeListState.Success -> {
            LazyColumn(
                modifier = modifier
            ) {
                itemsIndexed(
                    items = state.data,
                    key = { index, anime -> "${anime.id}-$index" },
                ) { _, anime ->
                    AnimeListItem(anime) { onAnimeClick(anime) }
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