package com.synchz.rick_morty.ui.location

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.synchz.rick_morty.BuildConfig
import com.synchz.rick_morty.domain.entities.Location
import com.synchz.rick_morty.domain.usecases.FetchLocationsListUseCase
import com.synchz.rick_morty.ui.common.Status
import com.synchz.rick_morty.utils.NetworkUtil
import kotlinx.coroutines.*
import java.net.UnknownHostException
import javax.inject.Inject

class LocationListBoundaryCallback @Inject constructor(
    private val fetchLocationListUseCase: FetchLocationsListUseCase,
    private val networkUtils: NetworkUtil
) : PagedList.BoundaryCallback<Location>() {
    private var totalCount: Int = 0
    private var isLoaded: Boolean = false
    var status: MutableLiveData<Status> = MutableLiveData()
    private val job: Job = Job()

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        if (!isLoaded) {
            fetchLocationsList(1)
        }
    }

    override fun onItemAtEndLoaded(itemAtEnd: Location) {
        Log.e("pk", "Item end " + itemAtEnd.id + " count $totalCount isLoaded $isLoaded")
        super.onItemAtEndLoaded(itemAtEnd)
        if (!isLoaded) {
            isLoaded = true
            fetchLocationsList(totalCount/BuildConfig.PAGE_SIZE)
        }
    }

    private fun fetchLocationsList(pageNo: Int) {
        if (!networkUtils.isInternetAvailable()) {
            updateState(Status.NETWORK_ERROR)
            return
        }
        if (pageNo == 1) {
            updateState(Status.LOADING)
        } else {
            updateState(Status.PAGE_LOADING)
        }
        CoroutineScope(Dispatchers.IO + job + CoroutineExceptionHandler { _, exception ->
            error(exception)
        }).launch {
            fetchLocationListUseCase.invoke(pageNo).collect {
                success(it)
            }
        }
    }

    private fun success(list: List<Location>?) {
        if (list == null) {
            isLoaded = true
            updateState(Status.LOADED)
            Log.e("PK", "list == null")
        } else {
            totalCount += list.size
            if (list.size < BuildConfig.PAGE_SIZE) {
                isLoaded = true
                updateState(Status.LOADED)
            } else {
                isLoaded = false
                updateState(Status.DONE)
            }
        }
    }

    private fun error(throwable: Throwable?) {
        throwable?.let {
            if (throwable is UnknownHostException) {
                updateState(Status.NETWORK_ERROR)
            } else {
                updateState(Status.ERROR)
            }
            isLoaded = false
        }
    }

    private fun updateState(state: Status) {
        this.status.postValue(state)
    }

    fun retry() {
        fetchLocationsList(totalCount/BuildConfig.PAGE_SIZE)
    }

    fun clear() {
        job.cancel()
    }

    fun onRefresh() {
        totalCount = 0
        isLoaded = false
        job.cancel()
        onZeroItemsLoaded()
    }
}