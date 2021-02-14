package com.example.xpmarvel.ui.characters.di

import com.example.xpmarvel.ui.characters.presentation.CharactersListFragment
import dagger.Subcomponent

@Subcomponent(modules = [CharacterModule::class])
interface CharacterComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): CharacterComponent
    }

    fun inject(fragment: CharactersListFragment)
}