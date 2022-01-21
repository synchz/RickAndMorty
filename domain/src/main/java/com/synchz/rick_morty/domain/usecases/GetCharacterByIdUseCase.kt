package com.synchz.rick_morty.domain.usecases

import com.synchz.rick_morty.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) :  BaseUseCase<Long, Flow<com.synchz.rick_morty.domain.entities.Character>> {

    override suspend operator fun invoke(params: Long) = characterRepository.getCharacterById(params)
}
