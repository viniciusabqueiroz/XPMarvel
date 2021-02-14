package com.example.xpmarvel.ui.favorites.view_model

import androidx.lifecycle.*
import com.example.xpmarvel.domain.models.FavoriteCharacterModel
import com.example.xpmarvel.domain.use_case.GetFavoriteCharacterUseCase
import com.example.xpmarvel.utils.Either
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    private val getFavoriteCharacterUseCase: GetFavoriteCharacterUseCase
) : ViewModel() {

    private val _items =
        MutableLiveData<List<FavoriteCharacterModel>>().apply { value = emptyList() }
    val items: LiveData<List<FavoriteCharacterModel>> = _items

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    val hasCharacters: LiveData<Boolean> = Transformations.map(items) {
        it.isNotEmpty()
    }

    fun loadData() {
        viewModelScope.launch {
            _dataLoading.value = true
            when (val favorites = getFavoriteCharacterUseCase.invoke()) {
                is Either.Left -> {

                }
                is Either.Right -> {
                    _items.value = favorites.b
                    _dataLoading.value = false
                }
            }
        }
    }
}