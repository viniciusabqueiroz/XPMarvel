package com.example.xpmarvel.data.repositories

import com.example.xpmarvel.data.local.mappers.CharacterEntityToFavoriteModelMapper
import com.example.xpmarvel.data.local.source.CharacterPersistenceSource
import com.example.xpmarvel.domain.models.FavoriteCharacterModel
import com.example.xpmarvel.domain.repositories.FavoriteRepository
import com.example.xpmarvel.domain.repositories.FavoriteRepositoryFailure
import com.example.xpmarvel.utils.Either
import com.example.xpmarvel.utils.Failure
import com.example.xpmarvel.utils.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val persistenceSource: CharacterPersistenceSource
) : FavoriteRepository {

    override suspend fun getFavoriteCharacter(): Either<Failure, List<FavoriteCharacterModel>> {
        return withContext(Dispatchers.IO) {
            try {
                val mapper =
                    persistenceSource.getFavoriteCharacter()
                        .map { CharacterEntityToFavoriteModelMapper.mapList(it) }
                Either.Right(mapper).b
            } catch (ex: Exception) {
                Either.Left(FavoriteRepositoryFailure.PersistenceError)
            }
        }
    }
}