package com.example.androidpractice.anime.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpractice.anime.domain.model.Anime
import com.example.androidpractice.anime.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn


class AnimeFavouriteViewModel(
    private val animeRepository: AnimeRepository
) : ViewModel() {
    val favouriteState: StateFlow<List<Anime>> = animeRepository.getFavouriteAnime()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = emptyList()
        )
}