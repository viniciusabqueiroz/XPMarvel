package com.example.xpmarvel.core.di

import android.content.Context
import com.example.xpmarvel.ui.character_detail.di.CharacterDetailComponent
import com.example.xpmarvel.ui.characters.di.CharacterComponent
import com.example.xpmarvel.ui.favorites.di.FavoritesComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        UseCaseModule::class,
        DataBaseModule::class,
        NetworkModule::class,
        ViewModelBuilderModule::class,
        SubcomponentsModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun characterComponent(): CharacterComponent.Factory
    fun characterDetailComponent(): CharacterDetailComponent.Factory
    fun favoriteCharacterComponent(): FavoritesComponent.Factory
}

@Module(
    subcomponents = [
        CharacterComponent::class
    ]
)
object SubcomponentsModule