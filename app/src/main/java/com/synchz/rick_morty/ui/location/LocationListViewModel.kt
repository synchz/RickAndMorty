package com.synchz.rick_morty.ui.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.synchz.rick_morty.BuildConfig
import com.synchz.rick_morty.domain.entities.Character
import com.synchz.rick_morty.domain.entities.Location
import com.synchz.rick_morty.domain.usecases.FetchLocationsListUseCase
import com.synchz.rick_morty.domain.usecases.GetLocationListUseCase
import com.synchz.rick_morty.ui.base.BaseViewModel
import com.synchz.rick_morty.utils.NetworkUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationListViewModel @Inject constructor(
    locationListUseCase: GetLocationListUseCase,
    fetchLocationListUseCase: FetchLocationsListUseCase,
    networkUtil: NetworkUtil
) : BaseViewModel() {

    var locationListSource: LiveData<PagedList<Location>> = MutableLiveData()
    var boundaryCallback = LocationListBoundaryCallback(fetchLocationListUseCase, networkUtil)

    init {
        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(BuildConfig.PAGE_SIZE)
            .setPageSize(BuildConfig.PAGE_SIZE)
            .setEnablePlaceholders(true).build()
        viewModelScope.launch {
            locationListSource = LivePagedListBuilder(
                locationListUseCase.invoke(Unit), config
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