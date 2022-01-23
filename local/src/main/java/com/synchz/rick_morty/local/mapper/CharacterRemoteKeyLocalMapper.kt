package com.synchz.rick_morty.local.mapper

import com.synchz.rick_morty.data.model.CharacterRemoteKeysEntity
import com.synchz.rick_morty.local.model.CharacterRemoteKeysLocal
import javax.inject.Inject

class CharacterRemoteKeyLocalMapper @Inject constructor(): Mapper<CharacterRemoteKeysLocal, CharacterRemoteKeysEntity> {

    override fun from(e: CharacterRemoteKeysEntity) = CharacterRemoteKeysLocal(
        e.characterId,
        e.prevKey,
        e.nextKey
    )

    override fun to(t: CharacterRemoteKeysLocal) = CharacterRemoteKeysEntity(
        t.characterId,
        t.prevKey,
        t.nextKey
    )
}