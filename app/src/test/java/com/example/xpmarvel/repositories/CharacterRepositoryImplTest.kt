package com.example.xpmarvel.repositories

import com.example.xpmarvel.data.network.MarvelService
import com.example.xpmarvel.data.local.source.CharacterPersistenceSource
import com.example.xpmarvel.data.network.models.characters.AllCharactersResponse
import com.example.xpmarvel.data.repositories.CharacterRepositoryImpl
import com.example.xpmarvel.helpers.mock
import com.example.xpmarvel.helpers.whenever
import com.example.xpmarvel.utils.Either
import com.example.xpmarvel.utils.Failure
import com.example.xpmarvel.utils.HashGeneratorImpl
import com.example.xpmarvel.utils.NetworkUtils
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
class CharacterRepositoryImplTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI Thread")

    @Mock
    private lateinit var service: MarvelService

    @Mock
    private lateinit var networkUtils: NetworkUtils

    @Mock
    lateinit var dataResponse: AllCharactersResponse

    @Mock
    lateinit var hashGenerator: HashGeneratorImpl

    @Mock
    lateinit var persistenceSource: CharacterPersistenceSource

    private lateinit var repository: CharacterRepositoryImpl

    @Before
    fun setup() {
        service = mock()
        networkUtils = mock()
        dataResponse = mock()
        hashGenerator = mock()
        persistenceSource = mock()
        repository =
            CharacterRepositoryImpl(
                service,
                networkUtils,
                hashGenerator,
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
    fun `when has no internet connection should return NetworkConnection Error`() {
        whenever(networkUtils.isInternetAvailable()).thenReturn(false)

        runBlocking {
            val result = repository.getCharacter()
            MatcherAssert.assertThat(result.isLeft, Is.`is`(true))
            MatcherAssert.assertThat(
                (result as Either.Left).a,
                CoreMatchers.instanceOf(Failure.NetworkConnection.javaClass)
            )
        }
    }
}