package com.example.xpmarvel.domain.repositories

import com.example.xpmarvel.domain.models.CharacterModel
import com.example.xpmarvel.utils.Either
import com.example.xpmarvel.utils.Failure
import com.example.xpmarvel.utils.Success

interface CharacterRepository {
    suspend fun getCharacter(): Either<Failure, List<CharacterModel>>

    suspend fun saveCharacter(character: CharacterModel): Either<Failure, Success>
}