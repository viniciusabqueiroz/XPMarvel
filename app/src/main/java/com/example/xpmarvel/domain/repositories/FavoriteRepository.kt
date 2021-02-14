package com.example.xpmarvel.domain.repositories

import com.example.xpmarvel.domain.models.FavoriteCharacterModel
import com.example.xpmarvel.utils.Either
import com.example.xpmarvel.utils.Failure

interface FavoriteRepository {
    suspend fun getFavoriteCharacter(): Either<Failure, List<FavoriteCharacterModel>>
}