package com.example.androidpractice.anime_list.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.androidpractice.AnimeDetails
import com.example.androidpractice.core.model.Anime
import com.example.androidpractice.core.model.MockData
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeDetailsDialog(anime: Anime) {
    ModalBottomSheet(onDismissRequest = {}) {

    }
}

@Composable
fun AnimeDetailsContent(anime: Anime) {
    val image = "C:\\Users\\Gamzat\\Downloads\\Initial_D_manga.jpg"

    Column(modifier = Modifier.padding(8.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = anime.title,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.headlineSmall
            )
            Icon(imageVector = Icons.Default.Star, contentDescription = null)
            Text(
                text = "%.2f".format(Locale.ENGLISH, anime.score),
                modifier = Modifier.padding(start = 2.dp),
                style = MaterialTheme.typography.titleMedium
            )
        }

        Text(
            text = anime.type,
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "Episodes: ${anime.episodes}",
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.titleSmall
        )
        Text(
            text = "Genres: ${anime.genres.joinToString()}",
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.titleSmall
        )
//        AsyncImage(
//            model = anime.imageUrl,
//            contentDescription = null
//        )
        Text(
            text = anime.description
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AnimeDetailsDialogPreview() {
    AnimeDetailsContent(MockData.getAnimeList().first())
}