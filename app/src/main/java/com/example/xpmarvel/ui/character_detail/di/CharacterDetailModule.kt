package com.example.xpmarvel.ui.character_detail.di

import androidx.lifecycle.ViewModel
import com.example.xpmarvel.ui.character_detail.view_model.CharacterDetailViewModel
import com.example.xpmarvel.core.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CharacterDetailModule {

    @Binds
    @IntoMap
    @ViewModelKey(CharacterDetailViewModel::class)
    abstract fun bindViewModel(viewModel: CharacterDetailViewModel): ViewModel
}