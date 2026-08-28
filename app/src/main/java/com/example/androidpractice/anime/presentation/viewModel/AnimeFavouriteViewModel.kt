package com.example.androidpractice.anime.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpractice.anime.domain.interactor.AnimeInteractor
import com.example.androidpractice.anime.domain.model.Anime
import com.example.androidpractice.anime.presentation.mapper.toUiModels
import com.example.androidpractice.anime.presentation.model.AnimeFavouriteViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class AnimeFavouriteViewModel(
    private val animeInteractor: AnimeInteractor
) : ViewModel() {
    private val mutableState = MutableStateFlow(AnimeFavouriteViewState())
    val viewState = mutableState.asStateFlow()

    init {
        loadFavouriteList()
    }

    private fun loadFavouriteList() {
        viewModelScope.launch {
            updateState(AnimeFavouriteViewState.State.Loading)

            animeInteractor.getFavouriteAnime()
                .catch { exception ->
                    val errorMessage = exception.localizedMessage ?: "Неизвестная ошибка БД"
                    updateState(AnimeFavouriteViewState.State.Error(errorMessage))
                }
                .collect { domainList ->
                    if (domainList.isEmpty())
                        updateState(AnimeFavouriteViewState.State.Empty)
                    else
                        updateState(AnimeFavouriteViewState.State.Success(domainList.toUiModels()))
                }
        }
    }

    fun onRetryClick() = loadFavouriteList()

    fun updateState(state: AnimeFavouriteViewState.State) = mutableState.update { it.copy(state = state) }
}