package com.synchz.rick_morty.remote.mapper

import com.synchz.rick_morty.data.model.LocationListEntity
import com.synchz.rick_morty.remote.model.LocationListModel
import javax.inject.Inject

class LocationListRemoteMapper @Inject constructor(
    private val infoRemoteMapper: InfoRemoteMapper,
    private val locationRemoteMapper: LocationRemoteMapper
) : Mapper<LocationListModel, LocationListEntity> {
    override fun from(e: LocationListEntity) = LocationListModel(
        info = infoRemoteMapper.from(e.info),
        results = e.results.map { locationRemoteMapper.from(it) }
    )

    override fun to(t: LocationListModel) = LocationListEntity (
        info = infoRemoteMapper.to(t.info),
        results = t.results.map { locationRemoteMapper.to(it) }
    )

}