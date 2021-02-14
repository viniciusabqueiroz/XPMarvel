package com.example.xpmarvel.data.network.models.mappers

import com.example.xpmarvel.data.network.models.characters.AllCharactersResponse
import com.example.xpmarvel.domain.models.ComicModel

object ComicsItemsMapper {

    fun map(item: AllCharactersResponse.Data.Result.Comics.Item) =
        ComicModel(
            name = item.name.orEmpty(),
            resourceURI = item.resourceURI.orEmpty()
        )
}