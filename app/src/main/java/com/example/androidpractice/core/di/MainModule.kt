package com.example.androidpractice.core.di

import com.example.androidpractice.MainList
import com.example.androidpractice.core.navigation.Route
import com.example.androidpractice.core.navigation.TopLevelBackStack
import org.koin.dsl.module

val mainModule = module {
    single { TopLevelBackStack<Route>(MainList) }
}