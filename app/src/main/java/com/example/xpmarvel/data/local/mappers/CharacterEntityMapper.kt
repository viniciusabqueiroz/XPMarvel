package com.example.xpmarvel.data.local.mappers

import com.example.xpmarvel.data.local.models.CharacterEntity
import com.example.xpmarvel.domain.models.CharacterModel

object CharacterEntityMapper {

    fun map(model: CharacterModel) =
        CharacterEntity(
            id = model.id,
            name = model.name,
            image = model.image
        )
}