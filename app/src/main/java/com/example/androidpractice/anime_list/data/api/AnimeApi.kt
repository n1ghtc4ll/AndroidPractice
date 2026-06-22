package com.example.androidpractice.anime_list.data.api

import com.example.androidpractice.anime_list.data.model.AnimeDto
import com.example.androidpractice.anime_list.data.model.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimeApi {
    @GET("top/anime")
    suspend fun getTopAnime(): Response<List<AnimeDto>>

    @GET("anime/{id}")
    suspend fun getAnimeById(@Path("id") id: Int): Response<AnimeDto>
}