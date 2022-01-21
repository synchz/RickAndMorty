package com.synchz.rick_morty.data.mapper

import com.synchz.rick_morty.data.model.CharacterEntity
import com.synchz.rick_morty.data.model.CharacterLocationEntity
import com.synchz.rick_morty.domain.entities.Character
import com.synchz.rick_morty.domain.entities.CharacterLocation

class CharacterMapper : Mapper<CharacterEntity, Character> {
    override fun from(character: Character) = CharacterEntity(
        id = character.id,
        name = character.name,
        status = character.status,
        species = character.species,
        type = character.type,
        gender = character.gender,
        origin = CharacterLocationEntity(
            name = character.origin.name,
            url = character.origin.url
        ),
        location = CharacterLocationEntity(
            name = character.location.name,
            url = character.location.url
        ),
        image = character.image,
        episode = character.episode,
        url = character.url,
        created = character.created
    )

    override fun to(characterEntity: CharacterEntity) = Character(
        id = characterEntity.id,
        name = characterEntity.name,
        status = characterEntity.status,
        species = characterEntity.species,
        type = characterEntity.type,
        gender = characterEntity.gender,
        origin = CharacterLocation(
            name = characterEntity.origin.name,
            url = characterEntity.origin.url
        ),
        location = CharacterLocation(
            name = characterEntity.location.name,
            url = characterEntity.location.url
        ),
        image = characterEntity.image,
        episode = characterEntity.episode,
        url = characterEntity.url,
        created = characterEntity.created
    )
}