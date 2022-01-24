package com.synchz.rick_morty.ui.episode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.synchz.rick_morty.BuildConfig
import com.synchz.rick_morty.domain.entities.Episode
import com.synchz.rick_morty.domain.usecases.FetchEpisodesListUseCase
import com.synchz.rick_morty.domain.usecases.GetEpisodeListUseCase
import com.synchz.rick_morty.ui.base.BaseViewModel
import com.synchz.rick_morty.utils.NetworkUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeListViewModel @Inject constructor(
    episodeListUseCase: GetEpisodeListUseCase,
    fetchEpisodeListUseCase: FetchEpisodesListUseCase,
    networkUtil: NetworkUtil
) : BaseViewModel() {

    var episodeListSource: LiveData<PagedList<Episode>> = MutableLiveData()
    var boundaryCallback = EpisodeListBoundaryCallback(fetchEpisodeListUseCase, networkUtil)

    init {
        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(BuildConfig.PAGE_SIZE)
            .setPageSize(BuildConfig.PAGE_SIZE)
            .setEnablePlaceholders(true).build()
        viewModelScope.launch {
            episodeListSource = LivePagedListBuilder(
                episodeListUseCase.invoke(Unit), config
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