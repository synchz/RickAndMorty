package com.synchz.rick_morty.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.synchz.rick_morty.BuildConfig
import com.synchz.rick_morty.domain.entities.Character
import com.synchz.rick_morty.domain.usecases.FetchCharacterListUseCase
import com.synchz.rick_morty.domain.usecases.GetCharacterListUseCase
import com.synchz.rick_morty.ui.base.BaseViewModel
import com.synchz.rick_morty.utils.NetworkUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    characterListUseCase: GetCharacterListUseCase,
    fetchCharacterListUseCase: FetchCharacterListUseCase,
    networkUtil: NetworkUtil
) : BaseViewModel() {

    var characterListSource: LiveData<PagedList<Character>> = MutableLiveData()
    var boundaryCallback = CharacterListBoundaryCallback(fetchCharacterListUseCase, networkUtil)

    init {
        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(BuildConfig.PAGE_SIZE)
            .setPageSize(BuildConfig.PAGE_SIZE)
            .setEnablePlaceholders(true).build()
        viewModelScope.launch {
            characterListSource = LivePagedListBuilder(
                characterListUseCase.invoke(Unit), config
            ).setBoundaryCallback(boundaryCallback).build()
        }
    }

    override fun onCleared() {
        super.onCleared()
        boundaryCallback.clear()
    }

    fun refreshList() {
        boundaryCallback.onRefresh()
    }

    fun retry(){
        boundaryCallback.retry()
    }
}