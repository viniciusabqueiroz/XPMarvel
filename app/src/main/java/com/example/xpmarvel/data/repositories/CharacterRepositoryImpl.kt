package com.example.xpmarvel.data.repositories

import com.example.xpmarvel.BuildConfig
import com.example.xpmarvel.data.local.source.CharacterPersistenceSource
import com.example.xpmarvel.data.network.MarvelService
import com.example.xpmarvel.data.network.models.mappers.CharacterModelMapper
import com.example.xpmarvel.domain.models.CharacterModel
import com.example.xpmarvel.domain.repositories.CharacterRepository
import com.example.xpmarvel.domain.repositories.CharacterRepositoryFailure
import com.example.xpmarvel.domain.repositories.CharacterRepositorySuccess
import com.example.xpmarvel.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val service: MarvelService,
    private val networkUtils: NetworkUtils,
    private val hashGenerator: HashGenerator,
    private val persistenceSource: CharacterPersistenceSource
) : CharacterRepository {

    override suspend fun getCharacter(): Either<Failure, List<CharacterModel>> {
        return when (networkUtils.isInternetAvailable()) {
            false -> {
                Either.Left(Failure.NetworkConnection)
            }
            true -> {
                loadCharacterFromNetwork()
            }
        }
    }

    private suspend fun loadCharacterFromNetwork(): Either<Failure, List<CharacterModel>> {
        return try {
            withContext(Dispatchers.IO) {
                val timeStamp = System.currentTimeMillis()
                val result = service.getCharacters(
                    hashGenerator.buildMD5Digest(
                        "" + timeStamp +
                                BuildConfig.PRIVATE_KEY + BuildConfig.PUBLIC_KEY
                    ),
                    timeStamp, 0
                )
                val mapper =
                    result.data?.results?.filterNotNull().orEmpty()
                        .map { CharacterModelMapper.map(it) }
                Either.Right(mapper)
            }
        } catch (ex: HttpException) {
            when (ex.code()) {
                409 -> Either.Left(CharacterRepositoryFailure.CharacterNotFound)
                else -> Either.Left(Failure.ServerError)
            }
        } catch (ex: Exception) {
            Either.Left(Failure.ServerError)
        }
    }

    override suspend fun saveCharacter(character: CharacterModel): Either<Failure, Success> {
        return withContext(Dispatchers.IO) {
            try {
                persistenceSource.saveCharacter(character)
                Either.Right(CharacterRepositorySuccess.PersistenceSuccess)

            } catch (ex: Exception) {
                Either.Left(CharacterRepositoryFailure.PersistenceError)
            }
        }
    }
}