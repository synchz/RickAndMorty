package com.synchz.rick_morty.ui.character

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.synchz.rick_morty.domain.entities.Character
import com.synchz.rick_morty.domain.usecases.FetchCharacterListUseCase
import com.synchz.rick_morty.domain.usecases.GetCharacterListUseCase
import com.synchz.rick_morty.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val characterListUseCase: GetCharacterListUseCase,
    private val fetchCharacterListUseCase: FetchCharacterListUseCase
) : BaseViewModel() {

    private val _characterList = MutableLiveData<List<Character>>()
    private var characterList: LiveData<List<Character>> = _characterList
    private val job: Job = Job()

    fun getCharactersList(): LiveData<List<Character>> {
        return characterList
    }

    fun getCharacters() {
        viewModelScope.launch(Dispatchers.IO + job + CoroutineExceptionHandler { _, exception ->
            Log.e("PK", exception.message ?: "Error Occurred")
        }) {

        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}