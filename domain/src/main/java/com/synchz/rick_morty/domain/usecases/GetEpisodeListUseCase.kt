package com.synchz.rick_morty.domain.usecases

import androidx.paging.DataSource
import com.synchz.rick_morty.domain.entities.Episode
import com.synchz.rick_morty.domain.repository.EpisodesRepository
import javax.inject.Inject

class GetEpisodeListUseCase @Inject constructor(
    private val episodesRepository: EpisodesRepository
) : BaseUseCase<Unit, DataSource.Factory<Int, Episode>> {

    override suspend operator fun invoke(params: Unit) = episodesRepository.getEpisodesDataSource()
}
