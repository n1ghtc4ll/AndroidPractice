package com.example.androidpractice.anime_list.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpractice.anime_list.domain.interactor.AnimeInteractor
import com.example.androidpractice.anime_list.presentation.model.AnimeUiModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class AnimeFavouritesViewModel(
    private val interactor: AnimeInteractor
) : ViewModel() {

    val favouritesList = interactor.getFavouriteAnimeList()
        .map { entityList ->
            entityList.map { entity ->
                AnimeUiModel(
                    id = entity.malId.toString(),
                    title = entity.title,
                    url = entity.url,
                    imageUrl = entity.imageUrl,
                    type = entity.type,
                    genres = if (entity.genres.isEmpty()) "No genres" else entity.genres.joinToString(),
                    episodes = entity.episodes.toString(),
                    score = entity.score.toString(),
                    description = entity.synopsis
                )
            }
        }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
}