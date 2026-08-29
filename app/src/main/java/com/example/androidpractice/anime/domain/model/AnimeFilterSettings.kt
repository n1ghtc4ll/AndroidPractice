package com.example.androidpractice.anime.domain.model


data class AnimeFilterSettings(
    val category: FilterSetting.Category,
    val releaseType: FilterSetting.ReleaseType,
    val ageRating: FilterSetting.AgeRating
)

sealed interface FilterSetting {
    val apiValue: String?
    val uiValue: String

    enum class Category(
        override val apiValue: String?,
        override val uiValue: String
    ) : FilterSetting {
        ALL(null, "All"),
        AIRING("airing", "Top Airing"),
        UPCOMING("upcoming", "Top Upcoming"),
        BY_POPULARITY("bypopularity", "Most Popular"),
        FAVORITE("favorite", "Most Favorited")
    }

    enum class ReleaseType(
        override val apiValue: String?,
        override val uiValue: String
    ) : FilterSetting {
        ALL(null, "All"),
        TV("TV", "TV"),
        OVA("OVA", "OVA"),
        MOVIE("Movie", "Movie"),
        SPECIAL("Special", "Special"),
        ONA("ONA", "ONA"),
        MUSIC("Music", "Music"),
        CM("CM", "CM"),
        PV("PV", "PV"),
        TV_SPECIAL("TV Special", "TV Special")
    }

    enum class AgeRating(
        override val apiValue: String?,
        override val uiValue: String
    ) : FilterSetting {
        ALL(null, "All"),
        G("g", "G - All Ages"),
        PG("pg", "PG - Children"),
        PG_13("pg13", "PG-13 - Teens 13 or older"),
        R_17("r17", "R - 17+ (violence & profanity)"),
        R("r", "R+ - Mild Nudity"),
    }
}
