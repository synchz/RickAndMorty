package com.synchz.rick_morty.local.mapper

import com.synchz.rick_morty.data.model.CharacterEntity
import com.synchz.rick_morty.data.model.CharacterLocationEntity
import com.synchz.rick_morty.local.model.CharacterLocal
import javax.inject.Inject

class CharacterLocalMapper @Inject constructor(): Mapper<CharacterLocal, CharacterEntity> {

    override fun from(characterEntity: CharacterEntity) = CharacterLocal(
        id = characterEntity.id,
        name = characterEntity.name,
        status = characterEntity.status,
        species = characterEntity.species,
        type = characterEntity.type,
        gender = characterEntity.gender,
        originName = characterEntity.origin.name,
            originUrl =  characterEntity.origin.url,
        locationName = characterEntity.location.name,
            locationUrl = characterEntity.location.url,
        image = characterEntity.image,
        episode = characterEntity.episode.joinToString(","),
        url = characterEntity.url,
        created = characterEntity.created
    )

    override fun to(characterLocal: CharacterLocal) = CharacterEntity(
        id = characterLocal.id,
        name = characterLocal.name,
        status = characterLocal.status,
        species = characterLocal.species,
        type = characterLocal.type,
        gender = characterLocal.gender,
        origin = CharacterLocationEntity(
            name = characterLocal.originName,
            url = characterLocal.originUrl
        ),
        location = CharacterLocationEntity(
            name = characterLocal.locationName,
            url = characterLocal.locationUrl
        ),
        image = characterLocal.image,
        episode = characterLocal.episode.split(","),
        url = characterLocal.url,
        created = characterLocal.created
    )
}