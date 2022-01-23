package com.synchz.rick_morty.domain.usecases

import androidx.paging.PagingData
import com.synchz.rick_morty.domain.entities.Character
import com.synchz.rick_morty.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterListDataSourceUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) : BaseUseCase<Unit, Flow<PagingData<Character>>> {

    override suspend operator fun invoke(params: Unit) = characterRepository.getCharactersDataSource()
}
