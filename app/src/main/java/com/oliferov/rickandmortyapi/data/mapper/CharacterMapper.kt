package com.oliferov.rickandmortyapi.data.mapper

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.oliferov.rickandmortyapi.data.database.CharacterDbModel
import com.oliferov.rickandmortyapi.data.network.model.CharacterDto
import com.oliferov.rickandmortyapi.data.network.model.PageDto
import com.oliferov.rickandmortyapi.domain.Character
import javax.inject.Inject

class CharacterMapper @Inject constructor(){

    private fun mapDtoToDbModel(dto: CharacterDto) = CharacterDbModel(
        id = dto.id,
        name = dto.name,
        species = dto.species,
        gender = dto.gender,
        status = dto.status,
        avatar = dto.avatar,
        location = dto.location.name,
        episode = dto.episode.size
    )

    fun mapDbModelToEntity(dbModel: CharacterDbModel) = Character(
        id = dbModel.id,
        name = dbModel.name,
        species = dbModel.species,
        gender = dbModel.gender,
        status = dbModel.status,
        avatarUrl = dbModel.avatar,
        location = dbModel.location,
        episode = dbModel.episode
    )

    fun mapDtoListToDbModelList(dtoList: List<CharacterDto>): List<CharacterDbModel> {
        return dtoList.map {
            mapDtoToDbModel(it)
        }
    }

    fun mapDbModelListToEntityList(dtoList: List<CharacterDbModel>): List<Character> {
        return dtoList.map {
            mapDbModelToEntity(it)
        }
    }

    fun mapJsonCharacterListToCharactersList(pageDto: PageDto): List<CharacterDto> {
        val result = mutableListOf<CharacterDto>()
        val jsonObjectList = pageDto.charactersList ?: return result
        jsonObjectList.map{
            result.add(Gson().fromJson(it,CharacterDto::class.java))
        }
        return result
    }
}