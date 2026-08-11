package com.example.androidpractice.anime.data.api

import com.example.androidpractice.anime.data.model.AnimeDto
import com.example.androidpractice.anime.data.model.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimeApi {
    @GET("top/anime")
    suspend fun getTopAnime(): Response<List<AnimeDto>>

    @GET("anime/{id}")
    suspend fun getAnimeById(@Path("id") id: Int): Response<AnimeDto>
}