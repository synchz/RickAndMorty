package com.synchz.rick_morty.domain.usecases

import com.synchz.rick_morty.domain.entities.Episode
import com.synchz.rick_morty.domain.repository.EpisodesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchEpisodesListUseCase @Inject constructor(
    private val episodesRepository: EpisodesRepository
) : BaseUseCase<Int, Flow<List<Episode>>> {

    override suspend operator fun invoke(params: Int) = episodesRepository.fetchEpisodesFromServer(params)
}
