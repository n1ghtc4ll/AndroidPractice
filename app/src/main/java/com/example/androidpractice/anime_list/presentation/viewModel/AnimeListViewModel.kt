package com.example.androidpractice.anime_list.presentation.viewModel

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.example.androidpractice.core.model.Anime
import com.example.androidpractice.core.model.MockData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AnimeListViewModel(
    animeList: List<Anime>
): ViewModel() {
    private val _animeList = MutableStateFlow(animeList)
    val animeList = _animeList.asStateFlow()
}