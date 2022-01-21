package com.synchz.rick_morty.domain.usecases

import com.synchz.rick_morty.domain.entities.Episode
import com.synchz.rick_morty.domain.repository.EpisodesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEpisodeByIdUseCase @Inject constructor(
    private val episodesRepository: EpisodesRepository
) :  BaseUseCase<Long, Flow<Episode>> {

    override suspend operator fun invoke(params: Long) = episodesRepository.getEpisodeById(params)
}
