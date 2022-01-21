package com.synchz.rick_morty.domain.usecases

import com.synchz.rick_morty.domain.entities.Location
import com.synchz.rick_morty.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetLocationListUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) : BaseUseCase<Int, Flow<List<Location>>> {

    override suspend operator fun invoke(params: Int) = locationRepository.getLocations(params)
}
