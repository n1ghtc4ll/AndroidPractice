package com.example.androidpractice.anime.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpractice.anime.domain.interactor.AnimeInteractor
import com.example.androidpractice.anime.presentation.model.AnimeDetailsViewState
import com.example.androidpractice.anime.presentation.model.AnimeUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AnimeDetailsViewModel(
    private val anime: AnimeUiModel,
    private val interactor: AnimeInteractor
) : ViewModel() {
    private val mutableState = MutableStateFlow(AnimeDetailsViewState(anime))
    val viewState = mutableState.asStateFlow()

    init {
        checkInitialFavouriteState()
    }

    private fun checkInitialFavouriteState() {
        viewModelScope.launch {
            val isFav = interactor.isAnimeFavourite(anime.id)
            mutableState.update { it.copy(isFavourite = isFav) }
        }
    }

    fun onFavouriteChanged() {
        val currentState = mutableState.value.isFavourite
        val newState = !currentState

        mutableState.update { it.copy(isFavourite = newState) }

        viewModelScope.launch {
            try {
                if (newState) {
                    interactor.addFavouriteAnime(anime.domainModel)
                }
                else {
                    interactor.deleteFavouriteAnime(anime.id)
                }
            }
            catch (e: Exception) {
                mutableState.update { it.copy(isFavourite = currentState) }
            }
        }
    }
}