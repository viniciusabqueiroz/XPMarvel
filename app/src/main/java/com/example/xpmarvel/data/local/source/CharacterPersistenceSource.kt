package com.example.xpmarvel.data.local.source

import com.example.xpmarvel.data.local.models.CharacterEntity
import com.example.xpmarvel.domain.models.CharacterModel
import com.example.xpmarvel.utils.Either
import com.example.xpmarvel.utils.Failure

interface CharacterPersistenceSource {
    suspend fun getFavoriteCharacter(): Either<Failure, List<CharacterEntity>>

    suspend fun saveCharacter(character: CharacterModel)

    suspend fun deleteCharacter(character: CharacterModel)
}