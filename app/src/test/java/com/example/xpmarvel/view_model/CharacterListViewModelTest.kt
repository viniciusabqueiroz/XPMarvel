package com.example.xpmarvel.view_model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.xpmarvel.ui.characters.view_model.CharactersListViewModel
import com.example.xpmarvel.domain.use_case.GetCharacterUseCase
import com.example.xpmarvel.domain.use_case.SaveCharacterUseCase
import com.example.xpmarvel.helpers.LiveDataTestUtil
import com.example.xpmarvel.helpers.MainCoroutineRule
import com.example.xpmarvel.helpers.mock
import com.example.xpmarvel.helpers.whenever
import com.example.xpmarvel.utils.Either
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class CharacterListViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getCharacterUseCase: GetCharacterUseCase

    @Mock
    private lateinit var saveCharacterUseCase: SaveCharacterUseCase

    private lateinit var viewModel: CharactersListViewModel

    @Before
    fun setUp() {
        getCharacterUseCase = mock()
        saveCharacterUseCase = mock()
        viewModel = CharactersListViewModel(getCharacterUseCase, saveCharacterUseCase)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `when load data should return Empty`() {
        mainCoroutineRule.pauseDispatcher()

        runBlocking {

            whenever(getCharacterUseCase.invoke())
                .thenReturn(Either.Right(listOf()))

            viewModel.loadData()
        }

        mainCoroutineRule.resumeDispatcher()

        assertThat(LiveDataTestUtil.getValue(viewModel.items)).isEmpty()
    }
}
