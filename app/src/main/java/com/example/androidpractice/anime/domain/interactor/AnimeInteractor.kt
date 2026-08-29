package com.example.androidpractice.anime.domain.interactor

import com.example.androidpractice.anime.domain.model.Anime
import com.example.androidpractice.anime.domain.model.AnimeFilterSettings
import com.example.androidpractice.anime.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow

class AnimeInteractor(
    private val repository: AnimeRepository
) {
    suspend fun getAnimeTopList() = repository.getAnimeTopList()
    suspend fun getAnimeById(id: Int) = repository.getAnimeById(id)

    fun getFavouriteAnime(): Flow<List<Anime>> = repository.getFavouriteAnime()
    suspend fun addFavouriteAnime(anime: Anime) = repository.addFavouriteAnime(anime)
    suspend fun deleteFavouriteAnime(id: Int) = repository.deleteFavouriteAnime(id)

    suspend fun isAnimeFavourite(id: Int) = repository.isAnimeFavourite(id)
    suspend fun saveFilters(settings: AnimeFilterSettings) = repository.saveFilters(settings)
    fun getFilters() = repository.getFilters()
}