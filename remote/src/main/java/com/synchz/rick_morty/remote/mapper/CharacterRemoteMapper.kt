package com.synchz.rick_morty.remote.mapper

import com.synchz.rick_morty.data.model.CharacterEntity
import com.synchz.rick_morty.data.model.CharacterLocationEntity
import com.synchz.rick_morty.remote.model.CharacterLocationModel
import com.synchz.rick_morty.remote.model.CharacterModel
import javax.inject.Inject

class CharacterRemoteMapper @Inject constructor() : Mapper<CharacterModel, CharacterEntity> {
    override fun from(characterEntity: CharacterEntity) = CharacterModel(
        id = characterEntity.id,
        name = characterEntity.name,
        status = characterEntity.status,
        species = characterEntity.species,
        type = characterEntity.type,
        gender = characterEntity.gender,
        origin = CharacterLocationModel(
            name = characterEntity.origin.name,
            url = characterEntity.origin.url
        ),
        location = CharacterLocationModel(
            name = characterEntity.location.name,
            url = characterEntity.location.url
        ),
        image = characterEntity.image,
        episode = characterEntity.episode,
        url = characterEntity.url,
        created = characterEntity.created
    )

    override fun to(characterModel: CharacterModel) = CharacterEntity(
        id = characterModel.id,
        name = characterModel.name,
        status = characterModel.status,
        species = characterModel.species,
        type = characterModel.type,
        gender = characterModel.gender,
        origin = CharacterLocationEntity(
            name = characterModel.origin.name,
            url = characterModel.origin.url
        ),
        location = CharacterLocationEntity(
            name = characterModel.location.name,
            url = characterModel.location.url
        ),
        image = characterModel.image,
        episode = characterModel.episode,
        url = characterModel.url,
        created = characterModel.created
    )
}