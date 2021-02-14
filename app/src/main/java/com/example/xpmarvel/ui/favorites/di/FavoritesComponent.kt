package com.example.xpmarvel.ui.favorites.di

import com.example.xpmarvel.ui.favorites.presentation.FavoritesFragment
import dagger.Subcomponent

@Subcomponent(modules = [FavoritesModule::class])

interface FavoritesComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): FavoritesComponent
    }

    fun inject(fragment: FavoritesFragment)
}