package com.example.androidpractice.anime_list.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpractice.anime_list.domain.interactor.AnimeInteractor
import com.example.androidpractice.anime_list.domain.model.AnimeEntity
import com.example.androidpractice.anime_list.presentation.model.AnimeDetailsViewState
import com.example.androidpractice.anime_list.presentation.model.AnimeUiModel
import com.example.androidpractice.navigation.Route
import com.example.androidpractice.navigation.TopLevelBackStack
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AnimeDetailsViewModel(
    private val topLevelBackStack: TopLevelBackStack<Route>,
    private val anime: AnimeUiModel,
    private val interactor: AnimeInteractor
) : ViewModel() {

    private val mutableState = MutableStateFlow(AnimeDetailsViewState(anime))
    val viewState = mutableState.asStateFlow()

    private var cachedEntity: AnimeEntity? = null

    init {
        val animeId = anime.id.toInt()

        viewModelScope.launch {
            interactor.isFavourite(animeId).collect { isFav ->
                mutableState.update { it.copy(isFavourite = isFav) }
            }
        }

        viewModelScope.launch {
            try {
                cachedEntity = interactor.getAnimeById(animeId)
            } catch (e: Exception) {

            }
        }
    }

    fun onFavouriteChanged() {
        viewModelScope.launch {
            val currentState = mutableState.value
            val animeId = anime.id.toInt()

            if (currentState.isFavourite) {
                interactor.deleteFavouriteAnime(animeId)
            } else {
                val entityToSend = cachedEntity ?: AnimeEntity(
                    malId = animeId,
                    url = anime.url,
                    imageUrl = anime.imageUrl,
                    title = anime.title,
                    type = anime.type,
                    episodes = anime.episodes.toIntOrNull() ?: 0,
                    status = "",
                    aired = null,
                    duration = "",
                    score = anime.score.toDoubleOrNull() ?: 0.0,
                    synopsis = anime.description,
                    genres = if (anime.genres.isEmpty()) emptyList() else anime.genres.split(", ")
                )
                interactor.saveFavouriteAnime(entityToSend)
            }
        }
    }

    fun onBack() = topLevelBackStack.removeLast()
}