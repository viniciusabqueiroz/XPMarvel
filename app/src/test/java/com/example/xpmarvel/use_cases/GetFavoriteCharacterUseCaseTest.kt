package com.example.xpmarvel.use_cases

import com.example.xpmarvel.domain.models.FavoriteCharacterModel
import com.example.xpmarvel.domain.repositories.FavoriteRepository
import com.example.xpmarvel.domain.use_case.GetFavoriteCharacterUseCase
import com.example.xpmarvel.helpers.mock
import com.example.xpmarvel.helpers.whenever
import com.example.xpmarvel.utils.Either
import com.example.xpmarvel.domain.repositories.FavoriteRepositoryFailure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito

@RunWith(JUnit4::class)
class GetFavoriteCharacterUseCaseTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI Thread")

    @Mock
    private lateinit var repository: FavoriteRepository

    private lateinit var useCase: GetFavoriteCharacterUseCase

    @Before
    fun setup() {
        repository = mock()
        useCase = GetFavoriteCharacterUseCase(repository)

        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `when get Favorite Character from empty Repo should return Error`() {
        runBlocking {
            whenever(repository.getFavoriteCharacter()).thenReturn(
                Either.Left(
                    FavoriteRepositoryFailure.PersistenceError
                )
            )

            val result = useCase()

            MatcherAssert.assertThat(result is Either.Left, CoreMatchers.`is`(true))
            MatcherAssert.assertThat(
                (result as Either.Left).a,
                CoreMatchers.instanceOf(FavoriteRepositoryFailure.PersistenceError.javaClass)
            )

            Mockito.verify(repository).getFavoriteCharacter()
        }
    }

    @Test
    fun `when get Favorite Character list from Repo with value should return value`() {
        val favoriteList = listOf(mock<FavoriteCharacterModel>(), mock())

        runBlocking {
            whenever(repository.getFavoriteCharacter()).thenReturn(Either.Right(favoriteList))

            val result = useCase()

            MatcherAssert.assertThat(result.isRight, CoreMatchers.`is`(true))
            MatcherAssert.assertThat((result as Either.Right).b.size, CoreMatchers.`is`(2))

            Mockito.verify(repository).getFavoriteCharacter()
        }
    }
}