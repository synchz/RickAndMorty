package com.synchz.rick_morty.local.source

import com.synchz.rick_morty.data.dataSource.LocalDataSource
import com.synchz.rick_morty.data.model.CharacterEntity
import com.synchz.rick_morty.data.model.CharacterRemoteKeysEntity
import com.synchz.rick_morty.data.model.EpisodeEntity
import com.synchz.rick_morty.data.model.LocationEntity
import com.synchz.rick_morty.local.database.dao.CharacterDao
import com.synchz.rick_morty.local.database.dao.EpisodeDao
import com.synchz.rick_morty.local.database.dao.LocationDao
import com.synchz.rick_morty.local.mapper.CharacterLocalMapper
import com.synchz.rick_morty.local.mapper.CharacterRemoteKeyLocalMapper
import com.synchz.rick_morty.local.mapper.EpisodeLocalMapper
import com.synchz.rick_morty.local.mapper.LocationLocalMapper
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val characterLocalMapper: CharacterLocalMapper,
    private val characterRemoteKeyLocalMapper: CharacterRemoteKeyLocalMapper,
    private val episodeLocalMapper: EpisodeLocalMapper,
    private val locationLocalMapper: LocationLocalMapper,
    private val characterDao: CharacterDao,
    private val episodeDao: EpisodeDao,
    private val locationDao: LocationDao
): LocalDataSource{

    override suspend fun saveCharacters(listCharacters: List<CharacterEntity>) {
        listCharacters.forEach {
            characterDao.saveCharacters(characterLocalMapper.from(it))
        }
    }

    override suspend fun saveCharactersCount(size: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getCharactersCount(): Int {
        TODO("Not yet implemented")
    }

    override suspend fun clearCharacters() {
        characterDao.clearCharacter()
    }

    override suspend fun getCharacterRemoteKeyById(id: Long): CharacterRemoteKeysEntity? {
        return characterDao.getCharacterRemoteKeyById(id)?.let{
            characterRemoteKeyLocalMapper.to(it)
        }
    }

    override suspend fun saveCharacterRemoteKey(key: CharacterRemoteKeysEntity) {
        characterDao.saveCharacterRemoteKey(characterRemoteKeyLocalMapper.from(key))
    }

    override suspend fun clearCharacterRemoteKey() {
        characterDao.clearCharacterRemoteKey()
    }

    override suspend fun saveLocations(listLocations: List<LocationEntity>) {
        listLocations.forEach {
            locationDao.saveLocation(locationLocalMapper.from(it))
        }
    }

    override suspend fun saveLocationsCount(size: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getLocationsCount(): Int {
        TODO("Not yet implemented")
    }

    override suspend fun clearLocations() {
        locationDao.clearLocations()
    }

    override suspend fun saveEpisodes(listEpisodes: List<EpisodeEntity>) {
        listEpisodes.forEach {
            episodeDao.saveEpisodes(episodeLocalMapper.from(it))
        }
    }

    override suspend fun saveEpisodeCount(size: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getEpisodeCount(): Int {
        TODO("Not yet implemented")
    }

    override suspend fun clearEpisodes() {
        episodeDao.clearEpisodes()
    }
}