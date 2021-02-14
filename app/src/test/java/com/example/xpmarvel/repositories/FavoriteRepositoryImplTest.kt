package com.example.xpmarvel.repositories

import com.example.xpmarvel.data.local.models.CharacterEntity
import com.example.xpmarvel.data.local.source.CharacterPersistenceSource
import com.example.xpmarvel.data.repositories.FavoriteRepositoryImpl
import com.example.xpmarvel.helpers.mock
import com.example.xpmarvel.utils.Either
import com.example.xpmarvel.domain.repositories.FavoriteRepositoryFailure
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock

@RunWith(JUnit4::class)
class FavoriteRepositoryImplTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI Thread")

    @Mock
    lateinit var persistenceSource: CharacterPersistenceSource

    private lateinit var repository: FavoriteRepositoryImpl

    private lateinit var character: CharacterEntity

    @Before
    fun setup() {
        persistenceSource = mock()
        character = mock()
        repository =
            FavoriteRepositoryImpl(
                persistenceSource
            )

        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `when get Favorite Character should return Persistence Error`() {

        runBlocking {
            val result = repository.getFavoriteCharacter()
            MatcherAssert.assertThat(result.isLeft, Is.`is`(true))
            MatcherAssert.assertThat(
                (result as Either.Left).a,
                CoreMatchers.instanceOf(FavoriteRepositoryFailure.PersistenceError.javaClass)
            )
        }
    }
}