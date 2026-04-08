package com.example.androidpractice.di

import com.example.androidpractice.MainList
import com.example.androidpractice.anime_list.presentation.viewModel.AnimeDetailsViewModel
import com.example.androidpractice.anime_list.presentation.viewModel.AnimeListViewModel
import com.example.androidpractice.core.model.MockData
import com.example.androidpractice.navigation.Route
import com.example.androidpractice.navigation.TopLevelBackStack
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single { MockData.getAnimeList() }
    single { TopLevelBackStack<Route>(MainList) }
    viewModel { AnimeListViewModel(get()) }
    //viewModel { AnimeDetailsViewModel(get()) }
}