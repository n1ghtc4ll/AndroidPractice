package com.example.androidpractice.anime_list.data.repository

import com.example.androidpractice.anime_list.data.api.AnimeApi
import com.example.androidpractice.anime_list.data.mapper.AnimeDtoToEntityMapper
import com.example.androidpractice.anime_list.domain.model.AnimeEntity
import com.example.androidpractice.anime_list.domain.repository.AnimeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AnimeRepositoryImpl(
    private val api: AnimeApi,
    private val mapper: AnimeDtoToEntityMapper
) : AnimeRepository {
    override suspend fun getAnimeById(id: Int): AnimeEntity? = withContext(Dispatchers.IO) {
        val response = api.getAnimeById(id)
        val dto = response.data ?: throw Exception()

        mapper.mapItem(dto)
    }

    override suspend fun getAnimeTopList(): List<AnimeEntity> = withContext(Dispatchers.IO) {
        val response = api.getTopAnime()
        val dtoList = response.data.orEmpty()

        mapper.mapList(dtoList)
    }
}