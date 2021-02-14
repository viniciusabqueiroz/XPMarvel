package com.example.xpmarvel.ui.characters.view_model

import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.example.xpmarvel.domain.models.CharacterModel
import com.example.xpmarvel.domain.use_case.GetCharacterUseCase
import com.example.xpmarvel.domain.use_case.SaveCharacterUseCase
import com.example.xpmarvel.utils.Either
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersListViewModel @Inject constructor(
    private val characterUseCase: GetCharacterUseCase,
    private val saveCharacterUseCase: SaveCharacterUseCase
) : ViewModel() {

    lateinit var openCharacterDetail: (CharacterModel) -> Unit
    lateinit var showToast: () -> Unit

    private val _items = MutableLiveData<List<CharacterModel>>().apply { value = emptyList() }
    val items: LiveData<List<CharacterModel>> = _items

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    val hasCharacters: LiveData<Boolean> = Transformations.map(items) {
        it.isNotEmpty()
    }

    fun loadData() {
        viewModelScope.launch {
            _dataLoading.value = true
            when (val characters = characterUseCase()) {
                is Either.Left -> {
                }
                is Either.Right -> {
                    _items.value = characters.b
                    _dataLoading.value = false
                }
            }
        }
    }

    fun saveFavorite(character: CharacterModel) = View.OnClickListener {
        viewModelScope.launch {
            when (saveCharacterUseCase.invoke(character)) {
                is Either.Left -> {
                    Log.d("VM SAVE", "FAILURE")
                }
                is Either.Right -> {
                    showToast()
                }
            }
        }
    }

    fun openCharacterDetails(character: CharacterModel) = View.OnClickListener {
        openCharacterDetail(character)
    }
}
