package com.example.androidpractice.anime_list.presentation.screen

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.androidpractice.R
import com.example.androidpractice.anime_list.presentation.model.AnimeUiModel
import com.example.androidpractice.MockData
import com.example.androidpractice.anime_list.presentation.model.AnimeDetailsViewState
import com.example.androidpractice.anime_list.presentation.viewModel.AnimeDetailsViewModel
import com.example.androidpractice.navigation.Route
import com.example.androidpractice.navigation.TopLevelBackStack
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import org.koin.viewmodel.factory.KoinViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeDetailsDialog(
    anime: AnimeUiModel
) {
    val viewModel = koinViewModel<AnimeDetailsViewModel> {
        parametersOf(anime)
    }
    val state by viewModel.viewState.collectAsStateWithLifecycle()

    ModalBottomSheet(onDismissRequest = { viewModel.onBack() }) {
        AnimeDetailsContent(state, viewModel::onFavouriteChanged)
    }
}

@Composable
fun AnimeDetailsContent(
    state: AnimeDetailsViewState,
    onFavouriteChanged: () -> Unit = {}
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        val (header, overviewBlock, description) = createRefs()
        val context = LocalContext.current

        Row(
            modifier = Modifier.constrainAs(header) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)

                width = Dimension.fillToConstraints
            },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = state.anime.title,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.weight(1f)
            )

            IconButton(
                onClick = { onFavouriteChanged() },
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    imageVector =
                        if (state.isFavourite) Icons.Default.Favorite
                        else Icons.Default.FavoriteBorder,
                    contentDescription = null
                )
            }
            
            IconButton(
                onClick = { shareText(context, state.anime.url) },
                modifier = Modifier.size(40.dp)
            ) {
                Icon(imageVector = Icons.Default.Share, contentDescription = null)
            }
        }

        Row(
            modifier = Modifier.constrainAs(overviewBlock) {
                top.linkTo(header.bottom, margin = 8.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)

                width = Dimension.fillToConstraints
            }
        ) {
            Column(
                modifier = Modifier
                    .padding(end = 4.dp)
                    .weight(3f)
            ) {
                val overviewStyle = MaterialTheme.typography.titleMedium
                val bottomPadding = Modifier.padding(bottom = 8.dp)

                Text(
                    text = state.anime.type,
                    style = overviewStyle,
                    modifier = bottomPadding,
                )
                Text(
                    text = "Episodes: ${state.anime.episodes}",
                    style = overviewStyle,
                    modifier = bottomPadding,
                )
                Text(
                    text = "Genres: ${state.anime.genres}",
                    style = overviewStyle,
                    modifier = bottomPadding,
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    Text(
                        text = state.anime.score,
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
            }
            AsyncImage(
                model = state.anime.imageUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
                    .aspectRatio(3f / 4f)
                    .weight(2f),
                contentScale = ContentScale.Fit,
                placeholder = ColorPainter(Color.LightGray),
                error = ColorPainter(Color.Gray)
            )
        }

        Text(
            text = state.anime.description,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.constrainAs(description) {
                top.linkTo(overviewBlock.bottom, margin = 8.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }
}

fun shareText(context: Context, text: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, text)
    }
    context.startActivity(Intent.createChooser(intent, "Поделиться через"))
}

@Preview(showBackground = true)
@Composable
fun AnimeDetailsDialogPreview() {
    AnimeDetailsContent(AnimeDetailsViewState(MockData.getAnimeList().first()))
}
