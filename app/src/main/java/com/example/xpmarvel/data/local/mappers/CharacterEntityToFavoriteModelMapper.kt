package com.example.xpmarvel.data.local.mappers

import com.example.xpmarvel.data.local.models.CharacterEntity
import com.example.xpmarvel.domain.models.FavoriteCharacterModel

object CharacterEntityToFavoriteModelMapper {

    fun map(entity: CharacterEntity) = FavoriteCharacterModel(
        name = entity.name,
        image = entity.image
    )

    fun mapList(list: List<CharacterEntity>): List<FavoriteCharacterModel> {
        return list.map { map(it) }
    }
}