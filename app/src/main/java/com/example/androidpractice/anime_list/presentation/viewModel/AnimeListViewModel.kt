package com.example.androidpractice.anime_list.presentation.viewModel

import androidx.compose.runtime.currentRecomposeScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpractice.AnimeDetails
import com.example.androidpractice.AnimeListFilter
import com.example.androidpractice.anime_list.domain.interactor.AnimeInteractor
import com.example.androidpractice.anime_list.domain.model.AnimeEntity
import com.example.androidpractice.anime_list.domain.model.AnimeFilter
import com.example.androidpractice.anime_list.presentation.cache.FilterBadgeCache
import com.example.androidpractice.anime_list.presentation.model.AnimeFilterModel
import com.example.androidpractice.anime_list.presentation.model.AnimeListViewState
import com.example.androidpractice.anime_list.presentation.model.AnimeUiModel
import com.example.androidpractice.navigation.Route
import com.example.androidpractice.navigation.TopLevelBackStack
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AnimeListViewModel(
    private val topLevelBackStack: TopLevelBackStack<Route>,
    private val interactor: AnimeInteractor,
    private val badgeCache: FilterBadgeCache
): ViewModel() {
    private val mutableState = MutableStateFlow(AnimeListViewState())
    val viewState = mutableState.asStateFlow()

    val isBadgeVisible = badgeCache.isBadgeVisible

    init {
        loadAnimeList()
    }

    fun loadAnimeList() {
        viewModelScope.launch {
            updateState(AnimeListViewState.State.Loading)

            try {
                val activeFilters = mutableState.value.activeFilters

                val animeList = interactor.getAnimeTopList(
                    type = activeFilters.type,
                    filter = activeFilters.filter,
                    rating = activeFilters.rating
                )

                updateState(AnimeListViewState.State.Success(mapToUI(animeList)))
            }
            catch (e: Exception) {
                updateState(AnimeListViewState.State.Error(e.localizedMessage ?: ""))
            }
        }
    }

    fun updateListFilters(newFilters: AnimeFilterModel) {
        mutableState.update {
            it.copy(dialogFilters = newFilters)
        }
    }

    fun onFilterClick() {
        mutableState.update { it.copy(dialogFilters = it.activeFilters) }
        topLevelBackStack.add(AnimeListFilter)
    }

    fun onApplyFilters() {
        val currentState = mutableState.value

        if (currentState.activeFilters != currentState.dialogFilters) {
            mutableState.update {
                it.copy(activeFilters = it.dialogFilters)
            }
            val hasFilters = currentState.dialogFilters != AnimeFilterModel()
            badgeCache.updateBadgeState(hasFilters)

            loadAnimeList()
        }

        topLevelBackStack.removeLast()
    }

    fun onDismissDialog() {
        updateListFilters(mutableState.value.activeFilters)
        topLevelBackStack.removeLast()
    }

    fun onAnimeClick(anime: AnimeUiModel) {
        topLevelBackStack.add(AnimeDetails(anime))
    }

    fun onRetryClick() {
        loadAnimeList()
    }

    fun updateState(state: AnimeListViewState.State) = mutableState.update { it.copy(state = state) }

    fun mapToUI(animeList: List<AnimeEntity>): List<AnimeUiModel> = animeList.map { 
        anime -> AnimeUiModel(
            id = anime.malId.toString(),
            title = anime.title,
            url = anime.url,
            imageUrl = anime.imageUrl,
            type = anime.type,
            genres = if (anime.genres.isEmpty()) "No genres" else anime.genres.joinToString(),
            episodes = anime.episodes.toString(),
            score = anime.score.toString(),
            description = anime.synopsis
        )
    }
}