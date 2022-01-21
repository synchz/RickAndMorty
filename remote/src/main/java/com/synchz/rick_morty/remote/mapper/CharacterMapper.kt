package com.synchz.rick_morty.remote.mapper

import com.synchz.rick_morty.data.model.CharacterEntity
import com.synchz.rick_morty.data.model.CharacterLocationEntity
import com.synchz.rick_morty.remote.model.CharacterLocationModel
import com.synchz.rick_morty.remote.model.CharacterModel

class CharacterMapper : Mapper<CharacterModel, CharacterEntity> {
    override fun from(character: CharacterEntity) = CharacterModel(
        id = character.id,
        name = character.name,
        status = character.status,
        species = character.species,
        type = character.type,
        gender = character.gender,
        origin = CharacterLocationModel(
            name = character.origin.name,
            url = character.origin.url
        ),
        location = CharacterLocationModel(
            name = character.location.name,
            url = character.location.url
        ),
        image = character.image,
        episode = character.episode,
        url = character.url,
        created = character.created
    )

    override fun to(characterEntity: CharacterModel) = CharacterEntity(
        id = characterEntity.id,
        name = characterEntity.name,
        status = characterEntity.status,
        species = characterEntity.species,
        type = characterEntity.type,
        gender = characterEntity.gender,
        origin = CharacterLocationEntity(
            name = characterEntity.origin.name,
            url = characterEntity.origin.url
        ),
        location = CharacterLocationEntity(
            name = characterEntity.location.name,
            url = characterEntity.location.url
        ),
        image = characterEntity.image,
        episode = characterEntity.episode,
        url = characterEntity.url,
        created = characterEntity.created
    )
}