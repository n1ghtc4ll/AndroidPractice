package com.example.androidpractice.anime.data.mapper

import com.example.androidpractice.anime.data.model.AnimeDto
import com.example.androidpractice.anime.domain.model.AiredData
import com.example.androidpractice.anime.domain.model.Anime
import java.time.LocalDate
import java.time.OffsetDateTime

class AnimeDtoToEntityMapper {
    fun mapList(list: List<AnimeDto>) : List<Anime> {
        return list.mapNotNull { anime -> mapItem(anime) }
    }

    fun mapItem(item: AnimeDto): Anime? = with(item) {
        Anime(
            malId = malId ?: return null,
            url = url.orEmpty(),
            imageUrl = images?.jpg?.imageUrl,
            title = titleEnglish ?: title ?: "Неизвестно",
            type = type ?: "Неизвестно",
            episodes = episodes ?: 0,
            status = status ?: "Неизвестно",
            aired = AiredData(
                parseDateOrNull(aired?.from),
                parseDateOrNull(aired?.to)
            ),
            duration = duration ?: "Неизвестно",
            score = score ?: 0.0,
            synopsis = synopsis ?: "Описание недоступно.",
            genres = genres?.mapNotNull { it.name } ?: emptyList(),
        )
    }

    private fun parseDateOrNull(date: String?) : LocalDate? {
        if (date.isNullOrEmpty()) return null

        return OffsetDateTime.parse(date).toLocalDate()
    }
}