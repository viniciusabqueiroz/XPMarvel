package com.example.xpmarvel.domain.use_case

import com.example.xpmarvel.domain.models.FavoriteCharacterModel
import com.example.xpmarvel.domain.repositories.FavoriteRepository
import com.example.xpmarvel.utils.Either
import com.example.xpmarvel.utils.Failure
import javax.inject.Inject

class GetFavoriteCharacterUseCase @Inject constructor(private val favoriteRepository: FavoriteRepository) {
    suspend operator fun invoke(): Either<Failure, List<FavoriteCharacterModel>> {
        return favoriteRepository.getFavoriteCharacter()
    }
}