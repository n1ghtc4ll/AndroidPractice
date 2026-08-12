package com.example.androidpractice.anime.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpractice.AnimeDetails
import com.example.androidpractice.anime.domain.interactor.AnimeInteractor
import com.example.androidpractice.anime.domain.model.Anime
import com.example.androidpractice.anime.presentation.model.AnimeListViewState
import com.example.androidpractice.anime.presentation.model.AnimeUiModel
import com.example.androidpractice.core.navigation.Route
import com.example.androidpractice.core.navigation.TopLevelBackStack
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AnimeListViewModel(
    private val topLevelBackStack: TopLevelBackStack<Route>,
    private val interactor: AnimeInteractor
): ViewModel() {
    private val mutableState = MutableStateFlow(AnimeListViewState())
    val viewState = mutableState.asStateFlow()

    init {
        loadAnimeList()
    }

    fun loadAnimeList() {
        viewModelScope.launch {
            updateState(AnimeListViewState.State.Loading)

            try {
                val animeList = interactor.getAnimeTopList()
                updateState(AnimeListViewState.State.Success(mapToUI(animeList)))
            }
            catch (e: Exception) {
                updateState(AnimeListViewState.State.Error(e.localizedMessage ?: ""))
            }
        }
    }

    fun onAnimeClick(anime: AnimeUiModel) {
        topLevelBackStack.add(AnimeDetails(anime))
    }

    fun onRetryClick() {
        loadAnimeList()
    }

    fun updateState(state: AnimeListViewState.State) = mutableState.update { it.copy(state = state) }

    fun mapToUI(animeList: List<Anime>): List<AnimeUiModel> = animeList.map {
        anime -> AnimeUiModel(
            id = anime.malId.toString(),
            title = anime.title,
            url = anime.url,
            imageUrl = anime.imageUrl,
            type = anime.type,
            genres = if (anime.genres.isEmpty()) "Нет жанров" else anime.genres.joinToString(),
            episodes = anime.episodes.toString(),
            score = anime.score.toString(),
            description = anime.synopsis
        )
    }
}