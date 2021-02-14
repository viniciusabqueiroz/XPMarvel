package com.example.xpmarvel.use_cases

import com.example.xpmarvel.domain.models.CharacterModel
import com.example.xpmarvel.domain.repositories.CharacterRepository
import com.example.xpmarvel.domain.use_case.GetCharacterUseCase
import com.example.xpmarvel.helpers.mock
import com.example.xpmarvel.helpers.whenever
import com.example.xpmarvel.domain.repositories.CharacterRepositoryFailure
import com.example.xpmarvel.utils.Either
import com.example.xpmarvel.utils.Failure
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
class GetCharacterUseCaseTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI Thread")

    @Mock
    private lateinit var repository: CharacterRepository

    private lateinit var useCase: GetCharacterUseCase

    @Before
    fun setup() {
        repository = mock()
        useCase = GetCharacterUseCase(repository)

        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `when get Character from empty Repo should return Error`() {
        runBlocking {
            whenever(repository.getCharacter()).thenReturn(Either.Left(CharacterRepositoryFailure.CharacterNotFound))

            val result = useCase()

            MatcherAssert.assertThat(result is Either.Left, CoreMatchers.`is`(true))
            MatcherAssert.assertThat(
                (result as Either.Left).a,
                CoreMatchers.instanceOf(CharacterRepositoryFailure.CharacterNotFound.javaClass)
            )

            Mockito.verify(repository).getCharacter()
        }
    }

    @Test
    fun `when get Character list from Repo with value should return value`() {
        val characterList = listOf(mock<CharacterModel>(), mock())

        runBlocking {
            whenever(repository.getCharacter()).thenReturn(Either.Right(characterList))

            val result = useCase()

            MatcherAssert.assertThat(result.isRight, CoreMatchers.`is`(true))
            MatcherAssert.assertThat((result as Either.Right).b.size, CoreMatchers.`is`(2))

            Mockito.verify(repository).getCharacter()
        }
    }

    @Test
    fun `when repository return error then should return Error`() {
        runBlocking {
            whenever(repository.getCharacter()).thenReturn(Either.Left(Failure.ServerError))

            val result = useCase()

            MatcherAssert.assertThat(result is Either.Left, CoreMatchers.`is`(true))
            MatcherAssert.assertThat(
                (result as Either.Left).a,
                CoreMatchers.instanceOf(Failure.ServerError.javaClass)
            )

            Mockito.verify(repository).getCharacter()
        }
    }
}