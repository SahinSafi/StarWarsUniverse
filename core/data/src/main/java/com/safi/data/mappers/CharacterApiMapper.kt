package com.safi.data.mappers

import com.safi.entity.CharacterListItemEntity
import com.safi.response.CharactersApiResponse
import javax.inject.Inject

class CharacterApiMapper @Inject constructor() : Mapper<CharactersApiResponse, List<CharacterListItemEntity>> {

    override fun mapFrom(type: CharactersApiResponse): List<CharacterListItemEntity> {
        return type.results.map {
            CharacterListItemEntity(
                birth_year = it.birth_year?:"",
                eye_color = it.eye_color?:"",
                gender = it.gender?:"",
                hair_color = it.hair_color?:"",
                height = it.height?:"",
                mass = it.mass?:"",
                name = it.name?:"",
                skin_color = it.skin_color?:"",
            )
        }
    }
}