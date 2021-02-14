package com.example.xpmarvel.data.network.models.mappers

import com.example.xpmarvel.data.network.models.characters.AllCharactersResponse
import com.example.xpmarvel.domain.models.SeriesModel

object SeriesItemsMapper {

    fun map(item: AllCharactersResponse.Data.Result.Series.Item) =
        SeriesModel(
            name = item.name.orEmpty(),
            resourceURI = item.resourceURI.orEmpty()
        )
}