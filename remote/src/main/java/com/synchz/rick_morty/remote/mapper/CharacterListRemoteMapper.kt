package com.synchz.rick_morty.remote.mapper

import com.synchz.rick_morty.data.model.CharacterListEntity
import com.synchz.rick_morty.remote.model.CharacterListModel
import javax.inject.Inject

class CharacterListRemoteMapper @Inject constructor(
    private val infoRemoteMapper: InfoRemoteMapper,
    private val characterRemoteMapper: CharacterRemoteMapper
) : Mapper<CharacterListModel, CharacterListEntity> {
    override fun from(e: CharacterListEntity) = CharacterListModel(
        info = infoRemoteMapper.from(e.info),
        results = e.results.map { characterRemoteMapper.from(it) }
    )

    override fun to(t: CharacterListModel) = CharacterListEntity (
        info = infoRemoteMapper.to(t.info),
        results = t.results.map { characterRemoteMapper.to(it) }
    )

}