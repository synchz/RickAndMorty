package com.synchz.rick_morty.remote.mapper

import com.synchz.rick_morty.data.model.LocationEntity
import com.synchz.rick_morty.remote.model.LocationModel

class LocationMapper : Mapper<LocationModel, LocationEntity> {
    override fun from(location: LocationEntity) = LocationModel(
        id = location.id,
        name = location.name,
        type = location.type,
        dimension = location.dimension,
        residents = location.residents,
        url = location.url,
        created = location.created
    )

    override fun to(locationEntity: LocationModel) = LocationEntity(
        id = locationEntity.id,
        name = locationEntity.name,
        type = locationEntity.type,
        dimension = locationEntity.dimension,
        residents = locationEntity.residents,
        url = locationEntity.url,
        created = locationEntity.created
    )
}