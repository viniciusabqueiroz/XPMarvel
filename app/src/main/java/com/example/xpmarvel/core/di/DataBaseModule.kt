package com.example.xpmarvel.core.di

import android.content.Context
import androidx.room.Room
import com.example.xpmarvel.data.network.MarvelService
import com.example.xpmarvel.data.local.MarvelDB
import com.example.xpmarvel.data.local.source.CharacterDao
import com.example.xpmarvel.data.local.source.CharacterPersistenceSource
import com.example.xpmarvel.data.local.source.CharacterPersistenceSourceImpl
import com.example.xpmarvel.domain.repositories.CharacterRepository
import com.example.xpmarvel.data.repositories.CharacterRepositoryImpl
import com.example.xpmarvel.domain.repositories.FavoriteRepository
import com.example.xpmarvel.data.repositories.FavoriteRepositoryImpl
import com.example.xpmarvel.utils.HashGenerator
import com.example.xpmarvel.utils.NetworkUtils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {
    @Singleton
    @Provides
    fun provideDb(context: Context): MarvelDB =
        Room.databaseBuilder(context.applicationContext, MarvelDB::class.java, "marvel.db")
            .fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideAllCharacterDao(db: MarvelDB): CharacterDao = db.allCharacterDao()

    @Singleton
    @Provides
    fun provideCharacterPersistenceSource(
        db: MarvelDB,
        dao: CharacterDao
    ): CharacterPersistenceSource = CharacterPersistenceSourceImpl(db, dao)

    @Singleton
    @Provides
    fun providesCharacterRepository(
        service: MarvelService,
        networkUtils: NetworkUtils,
        hashGenerator: HashGenerator,
        persistenceSource: CharacterPersistenceSource
    ): CharacterRepository =
        CharacterRepositoryImpl(
            service,
            networkUtils,
            hashGenerator,
            persistenceSource
        )

    @Singleton
    @Provides
    fun providesFavoriteRepository(
        persistenceSource: CharacterPersistenceSource
    ): FavoriteRepository =
        FavoriteRepositoryImpl(
            persistenceSource
        )
}