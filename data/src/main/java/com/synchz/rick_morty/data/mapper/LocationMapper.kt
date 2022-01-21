package com.synchz.rick_morty.data.mapper

import com.synchz.rick_morty.data.model.LocationEntity
import com.synchz.rick_morty.domain.entities.Location

class LocationMapper : Mapper<LocationEntity, Location> {
    override fun from(location: Location) = LocationEntity(
        id = location.id,
        name = location.name,
        type = location.type,
        dimension = location.dimension,
        residents = location.residents,
        url = location.url,
        created = location.created
    )

    override fun to(locationEntity: LocationEntity) = Location(
        id = locationEntity.id,
        name = locationEntity.name,
        type = locationEntity.type,
        dimension = locationEntity.dimension,
        residents = locationEntity.residents,
        url = locationEntity.url,
        created = locationEntity.created
    )
}