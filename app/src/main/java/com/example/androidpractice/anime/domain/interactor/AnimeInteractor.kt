package com.example.androidpractice.anime.domain.interactor

import com.example.androidpractice.anime.domain.repository.AnimeRepository

class AnimeInteractor(
    private val repository: AnimeRepository
) {
    suspend fun getAnimeTopList() = repository.getAnimeTopList()

    suspend fun getAnimeById(id: Int) = repository.getAnimeById(id)
}