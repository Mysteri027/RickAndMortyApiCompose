package com.igor.composestudy.presentation.screens.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.igor.composestudy.domain.usecase.GetCharacterListUseCase
import com.igor.composestudy.presentation.mapper.CharacterUiMapper
import com.igor.composestudy.presentation.model.CharacterUiModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel(
    private val mapper: CharacterUiMapper,
    private val getCharacterListUseCase: GetCharacterListUseCase,
) : ViewModel() {

    private val _characterList = MutableLiveData<List<CharacterUiModel>>()
    val characterList: LiveData<List<CharacterUiModel>> = _characterList

    fun getCharacterList() {
        CoroutineScope(Dispatchers.IO).launch {
            val characters = getCharacterListUseCase.invoke()
            _characterList.postValue(characters.map {
                mapper.map(it)
            })
        }
    }
}