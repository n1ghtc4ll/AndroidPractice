package com.example.androidpractice.anime_list.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidpractice.AnimeDetails
import com.example.androidpractice.MainList
import com.example.androidpractice.core.model.MockData
import com.example.androidpractice.core.model.Anime
import com.example.androidpractice.navigation.Route
import com.example.androidpractice.navigation.TopLevelBackStack
import java.util.Locale

@Composable
fun AnimeListScreen(topLevelBackStack: TopLevelBackStack<Route>) {
    val animeTitles = remember { MockData.getAnimeList() }

    LazyColumn() {
        animeTitles.forEach { anime ->
            item(key = anime.id) {
                AnimeListItem(anime) { topLevelBackStack.add(AnimeDetails(anime)) }
            }
        }
    }
}

@Composable
fun AnimeListItem(anime: Anime, onAnimeClick: (Anime) -> Unit) {
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
                text = "%.2f".format(Locale.ENGLISH, anime.score),
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
            text = "Genres: ${anime.genres.joinToString()}",
            style = MaterialTheme.typography.bodyMedium
        )

        HorizontalDivider(modifier = Modifier.padding(top = 4.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun AnimeListScreenPreview(){
    AnimeListScreen(TopLevelBackStack<Route>(MainList))
}