package com.synchz.rick_morty.domain.usecases

import androidx.paging.DataSource
import com.synchz.rick_morty.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharacterListUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) : BaseUseCase<Unit, DataSource.Factory<Int, com.synchz.rick_morty.domain.entities.Character>> {

    override suspend operator fun invoke(params: Unit) = characterRepository.getCharactersDataSource()
}
