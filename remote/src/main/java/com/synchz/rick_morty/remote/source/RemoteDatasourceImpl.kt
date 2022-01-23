package com.synchz.rick_morty.remote.source

import com.synchz.rick_morty.data.dataSource.RemoteDatasource
import com.synchz.rick_morty.data.model.*
import com.synchz.rick_morty.remote.api.CharacterService
import com.synchz.rick_morty.remote.api.EpisodeService
import com.synchz.rick_morty.remote.api.LocationService
import com.synchz.rick_morty.remote.mapper.*
import javax.inject.Inject

class RemoteDatasourceImpl @Inject constructor(
    private val characterRemoteMapper: CharacterRemoteMapper,
    private val characterListRemoteMapper: CharacterListRemoteMapper,
    private val episodeRemoteMapper: EpisodeRemoteMapper,
    private val episodeListRemoteMapper: EpisodeListRemoteMapper,
    private val locationRemoteMapper: LocationRemoteMapper,
    private val locationListRemoteMapper: LocationListRemoteMapper,
    private val characterService: CharacterService,
    private val locationService: LocationService,
    private val episodeService: EpisodeService
) : RemoteDatasource {
    override suspend fun getCharacters(pageNo: Int): CharacterListEntity =
        characterService.getAllCharacters(pageNo).let {
            characterListRemoteMapper.to(it)
        }

    override suspend fun getCharacterById(characterId: Long): CharacterEntity =
        characterService.getCharacterById(characterId).let {
            characterRemoteMapper.to(it)
        }

    override suspend fun getAllLocations(pageNo: Int): LocationListEntity =
        locationService.getAllLocations(pageNo).let {
            locationListRemoteMapper.to(it)
        }

    override suspend fun getLocationById(locationId: Long): LocationEntity =
        locationService.getLocationById(locationId).let {
            locationRemoteMapper.to(it)
        }

    override suspend fun getEpisodes(pageNo: Int): EpisodeListEntity =
        episodeService.getAllEpisodes(pageNo).let {
            episodeListRemoteMapper.to(it)
        }

    override suspend fun getEpisodeById(episodeId: Long): EpisodeEntity =
        episodeService.getEpisodeById(episodeId).let {
            episodeRemoteMapper.to(it)
        }
}