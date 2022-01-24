package com.synchz.rick_morty.domain.usecases

import androidx.paging.DataSource
import com.synchz.rick_morty.domain.entities.Location
import com.synchz.rick_morty.domain.repository.LocationRepository
import javax.inject.Inject


class GetLocationListUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) : BaseUseCase<Unit, DataSource.Factory<Int,Location>> {

    override suspend operator fun invoke(params: Unit) = locationRepository.getLocationsDataSource()
}
