package com.example.xpmarvel.ui.characters.di

import androidx.lifecycle.ViewModel
import com.example.xpmarvel.ui.characters.view_model.CharactersListViewModel
import com.example.xpmarvel.core.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CharacterModule {
    @Binds
    @IntoMap
    @ViewModelKey(CharactersListViewModel::class)
    abstract fun bindViewModel(viewModel: CharactersListViewModel): ViewModel
}