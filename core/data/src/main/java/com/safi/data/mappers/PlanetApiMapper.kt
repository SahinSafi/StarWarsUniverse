package com.safi.data.mappers

import com.safi.entity.PlanetListItemEntity
import com.safi.response.PlanetApiResponse
import javax.inject.Inject

class PlanetApiMapper @Inject constructor() : Mapper<PlanetApiResponse, List<PlanetListItemEntity>> {

    override fun mapFrom(type: PlanetApiResponse): List<PlanetListItemEntity> {
        return type.results.map {
            PlanetListItemEntity(
                climate = it.climate?:"",
                diameter = it.diameter?:"",
                gravity = it.gravity?:"",
                name = it.name?:"",
                orbital_period = it.orbital_period?:"",
                population = it.population?:"",
                rotation_period = it.rotation_period?:"",
                surface_water = it.surface_water?:"",
                terrain = it.terrain?:"",
            )
        }
    }
}