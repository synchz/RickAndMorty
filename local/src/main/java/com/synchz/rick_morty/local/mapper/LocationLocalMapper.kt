package com.synchz.rick_morty.local.mapper

import com.synchz.rick_morty.data.model.LocationEntity
import com.synchz.rick_morty.local.model.LocationLocal
import javax.inject.Inject

class LocationLocalMapper @Inject constructor() : Mapper<LocationLocal, LocationEntity> {
    override fun from(location: LocationEntity) = LocationLocal(
        id = location.id,
        name = location.name,
        type = location.type,
        dimension = location.dimension,
        residents = location.residents.joinToString(","),
        url = location.url,
        created = location.created
    )

    override fun to(location: LocationLocal) = LocationEntity(
        id = location.id,
        name = location.name,
        type = location.type,
        dimension = location.dimension,
        residents = location.residents.split(","),
        url = location.url,
        created = location.created
    )
}