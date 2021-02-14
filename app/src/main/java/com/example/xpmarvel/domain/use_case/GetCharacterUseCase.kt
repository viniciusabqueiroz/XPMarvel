package com.example.xpmarvel.domain.use_case

import com.example.xpmarvel.domain.models.CharacterModel
import com.example.xpmarvel.domain.repositories.CharacterRepository
import com.example.xpmarvel.utils.Either
import com.example.xpmarvel.utils.Failure
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(private val characterRepository: CharacterRepository) {
    suspend operator fun invoke(): Either<Failure, List<CharacterModel>> {
        return characterRepository.getCharacter()
    }
}