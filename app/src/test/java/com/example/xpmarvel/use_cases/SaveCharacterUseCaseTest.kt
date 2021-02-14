package com.example.xpmarvel.use_cases

import com.example.xpmarvel.domain.models.CharacterModel
import com.example.xpmarvel.domain.repositories.CharacterRepository
import com.example.xpmarvel.domain.use_case.SaveCharacterUseCase
import com.example.xpmarvel.helpers.mock
import com.example.xpmarvel.helpers.whenever
import com.example.xpmarvel.domain.repositories.CharacterRepositoryFailure
import com.example.xpmarvel.domain.repositories.CharacterRepositorySuccess
import com.example.xpmarvel.utils.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
class SaveCharacterUseCaseTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI Thread")

    @Mock
    private lateinit var repository: CharacterRepository

    private lateinit var useCase: SaveCharacterUseCase

    private lateinit var character: CharacterModel

    @Before
    fun setup() {
        repository = mock()
        character = mock()
        useCase = SaveCharacterUseCase(repository)

        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `when get  save Character from empty Repo should return Error`() {
        runBlocking {
            whenever(repository.saveCharacter(character)).thenReturn(
                Either.Left(
                    CharacterRepositoryFailure.PersistenceError
                )
            )

            val result = useCase(character)

            MatcherAssert.assertThat(result is Either.Left, CoreMatchers.`is`(true))
            MatcherAssert.assertThat(
                (result as Either.Left).a,
                CoreMatchers.instanceOf(CharacterRepositoryFailure.PersistenceError.javaClass)
            )

            Mockito.verify(repository).saveCharacter(character)
        }
    }

    @Test
    fun `when get save Character  in Repo with value should return Success`() {
        runBlocking {
            launch(Dispatchers.Main) {
                whenever(repository.saveCharacter(character)).thenReturn(
                    Either.Right(
                        CharacterRepositorySuccess.PersistenceSuccess
                    )
                )

                val result = useCase.invoke(character)

                MatcherAssert.assertThat(result.isRight, CoreMatchers.`is`(true))
                MatcherAssert.assertThat(
                    (result as Either.Right).b,
                    CoreMatchers.instanceOf(CharacterRepositorySuccess.PersistenceSuccess.javaClass)
                )

                Mockito.verify(repository).saveCharacter(character)
            }
        }
    }
}