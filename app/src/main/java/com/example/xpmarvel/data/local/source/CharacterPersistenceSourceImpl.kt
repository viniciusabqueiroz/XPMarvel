package com.example.xpmarvel.data.local.source

import com.example.xpmarvel.data.local.MarvelDB
import com.example.xpmarvel.data.local.RoomTransaction
import com.example.xpmarvel.data.local.mappers.CharacterEntityMapper
import com.example.xpmarvel.data.local.models.CharacterEntity
import com.example.xpmarvel.domain.models.CharacterModel
import com.example.xpmarvel.utils.Either
import com.example.xpmarvel.utils.Failure
import javax.inject.Inject

class CharacterPersistenceSourceImpl @Inject constructor(
    private val db: MarvelDB,
    private val dao: CharacterDao
) : CharacterPersistenceSource {

    override suspend fun getFavoriteCharacter(): Either<Failure, List<CharacterEntity>> {
        return Either.Right(dao.getFavorites())
    }

    override suspend fun saveCharacter(character: CharacterModel) {
        RoomTransaction.execute(db) {
            dao.save(CharacterEntityMapper.map(character))
        }
    }

    override suspend fun deleteCharacter(character: CharacterModel) {
        RoomTransaction.execute(db) {
            dao.delete(CharacterEntityMapper.map(character))
        }
    }
}