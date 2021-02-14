package com.example.xpmarvel.ui.favorites.di

import androidx.lifecycle.ViewModel
import com.example.xpmarvel.core.di.ViewModelKey
import com.example.xpmarvel.ui.favorites.view_model.FavoritesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FavoritesModule {
    @Binds
    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    abstract fun bindViewModel(viewModel: FavoritesViewModel): ViewModel
}
