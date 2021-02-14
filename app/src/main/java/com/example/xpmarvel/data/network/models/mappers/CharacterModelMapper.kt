package com.example.xpmarvel.data.network.models.mappers

import com.example.xpmarvel.data.network.models.characters.AllCharactersResponse
import com.example.xpmarvel.domain.models.CharacterModel

object CharacterModelMapper {

    fun map(response: AllCharactersResponse.Data.Result) =
        CharacterModel(
            id = response.id ?: 0,
            name = response.name.orEmpty(),
            image = "${response.thumbnail?.path}.${response.thumbnail?.extension}",
            description = if (response.description.isNullOrEmpty()) response.description.orEmpty() else "No description",
            comics = response.comics?.items?.filterNotNull().orEmpty()
                .map { ComicsItemsMapper.map(it) },
            series = response.series?.items?.filterNotNull().orEmpty()
                .map { SeriesItemsMapper.map(it) }
        )
}