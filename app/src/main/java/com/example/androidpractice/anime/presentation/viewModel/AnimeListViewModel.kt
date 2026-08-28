package com.example.androidpractice.anime.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpractice.anime.domain.interactor.AnimeInteractor
import com.example.androidpractice.anime.domain.model.Anime
import com.example.androidpractice.anime.presentation.mapper.toUiModels
import com.example.androidpractice.anime.presentation.model.AnimeListViewState
import com.example.androidpractice.anime.presentation.model.AnimeUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AnimeListViewModel(
    private val interactor: AnimeInteractor
): ViewModel() {
    private val mutableState = MutableStateFlow(AnimeListViewState())
    val viewState = mutableState.asStateFlow()

    init {
        loadAnimeList()
    }

    private fun loadAnimeList() {
        viewModelScope.launch {
            updateState(AnimeListViewState.State.Loading)

            try {
                val animeList = interactor.getAnimeTopList()
                updateState(AnimeListViewState.State.Success(animeList.toUiModels()))
            }
            catch (e: Exception) {
                updateState(AnimeListViewState.State.Error(e.localizedMessage ?: ""))
            }
        }
    }

    fun onRetryClick() {
        loadAnimeList()
    }

    fun updateState(state: AnimeListViewState.State) = mutableState.update { it.copy(state = state) }
}