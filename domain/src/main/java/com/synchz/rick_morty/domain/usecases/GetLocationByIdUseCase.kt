package com.synchz.rick_morty.domain.usecases

import com.synchz.rick_morty.domain.entities.Location
import com.synchz.rick_morty.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocationByIdUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) :  BaseUseCase<Long, Flow<Location>> {

    override suspend operator fun invoke(params: Long) = locationRepository.getLocationById(params)
}
