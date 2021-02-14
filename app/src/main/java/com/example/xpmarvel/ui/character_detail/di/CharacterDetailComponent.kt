package com.example.xpmarvel.ui.character_detail.di

import com.example.xpmarvel.ui.character_detail.presentation.CharacterDetailFragment
import dagger.Subcomponent

@Subcomponent(modules = [CharacterDetailModule::class])
interface CharacterDetailComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): CharacterDetailComponent
    }

    fun inject(fragment: CharacterDetailFragment)
}