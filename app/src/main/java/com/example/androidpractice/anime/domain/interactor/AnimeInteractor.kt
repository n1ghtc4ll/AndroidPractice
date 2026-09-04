package com.example.androidpractice.anime.domain.interactor

import com.example.androidpractice.anime.domain.model.Anime
import com.example.androidpractice.anime.domain.model.AnimeFilterSettings
import com.example.androidpractice.anime.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow

class AnimeInteractor(
    private val repository: AnimeRepository
) {
    suspend fun getAnimeTopList(
        filter: AnimeFilterSettings = AnimeFilterSettings()
    ) = repository.getAnimeTopList(filter)
    suspend fun getAnimeById(id: Int) = repository.getAnimeById(id)

    fun getFavouriteAnime(
        filter: AnimeFilterSettings = AnimeFilterSettings()
    ): Flow<List<Anime>> = repository.getFavouriteAnime(filter)
    suspend fun addFavouriteAnime(anime: Anime) = repository.addFavouriteAnime(anime)
    suspend fun deleteFavouriteAnime(id: Int) = repository.deleteFavouriteAnime(id)

    suspend fun isAnimeFavourite(id: Int) = repository.isAnimeFavourite(id)
    suspend fun saveFilters(settings: AnimeFilterSettings) = repository.saveFilters(settings)
    fun getFilters() = repository.getFilters()
}