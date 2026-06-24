package com.example.androidpractice.anime_list.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Response<T>(
    val data: T?,
    val pagination: Pagination? = null
)

@Serializable
data class AnimeDto(
    @SerialName("mal_id") val malId: Int?,
    @SerialName("url") val url: String?,
    @SerialName("images") val images: AnimeImagesDto?,
    @SerialName("title") val title: String?,
    @SerialName("title_english") val titleEnglish: String?,
    @SerialName("type") val type: String?,
    @SerialName("episodes") val episodes: Int?,
    @SerialName("status") val status: String?,
    @SerialName("aired") val aired: AiredDto?,
    @SerialName("duration") val duration: String?,
    @SerialName("score") val score: Double?,
    @SerialName("synopsis") val synopsis: String?,
    @SerialName("genres") val genres: List<Genre>?,
)

@Serializable
data class AnimeImagesDto(
    @SerialName("jpg") val jpg: JpgImageUrlsDto?
)

@Serializable
data class JpgImageUrlsDto(
    @SerialName("image_url") val imageUrl: String?
)

@Serializable
data class AiredDto(
    @SerialName("from") val from: String?,
    @SerialName("to") val to: String?
)

@Serializable
data class Genre(
    @SerialName("name") val name: String?
)

@Serializable
data class Pagination(
    @SerialName("last_visible_page") val lastVisiblePage: Int?,
    @SerialName("has_next_page") val hasNextPage: Boolean?,
    @SerialName("current_page") val currentPage: Int?
)