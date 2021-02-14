package com.example.xpmarvel.domain.use_case

import com.example.xpmarvel.domain.models.CharacterModel
import com.example.xpmarvel.domain.repositories.CharacterRepository
import com.example.xpmarvel.utils.Either
import com.example.xpmarvel.utils.Failure
import com.example.xpmarvel.utils.Success
import javax.inject.Inject

class SaveCharacterUseCase @Inject constructor(private val characterRepository: CharacterRepository) {
    suspend operator fun invoke(character: CharacterModel): Either<Failure, Success> {
        return characterRepository.saveCharacter(character)
    }
}