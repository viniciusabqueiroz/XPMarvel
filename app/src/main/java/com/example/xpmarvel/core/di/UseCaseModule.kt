package com.example.xpmarvel.core.di

import com.example.xpmarvel.domain.repositories.CharacterRepository
import com.example.xpmarvel.domain.use_case.GetCharacterUseCase
import com.example.xpmarvel.domain.use_case.SaveCharacterUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
    @Provides
    internal fun providesGetCharacterUseCase(
        repository: CharacterRepository
    ): GetCharacterUseCase = GetCharacterUseCase(repository)

    @Provides
    internal fun providesSaveCharacterUseCase(
        repository: CharacterRepository
    ): SaveCharacterUseCase = SaveCharacterUseCase(repository)
}