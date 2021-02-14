package com.example.xpmarvel.data.network

import com.example.xpmarvel.data.network.models.characters.AllCharactersResponse
import retrofit2.http.GET
import retrofit2.http.Query

const val HASH = "hash"
const val TIMESTAMP = "ts"
const val OFFSET = "offset"

interface MarvelService {
    @GET("characters")
    suspend fun getCharacters(
        @Query(HASH) md5Digest: String,
        @Query(TIMESTAMP) timestamp: Long,
        @Query(OFFSET) offset: Int?
    ): AllCharactersResponse
}