package com.safi.data.mappers

import com.safi.entity.StarshipListItemEntity
import com.safi.response.StarshipApiResponse
import javax.inject.Inject

class StarshipApiMapper @Inject constructor() : Mapper<StarshipApiResponse, List<StarshipListItemEntity>> {

    override fun mapFrom(type: StarshipApiResponse): List<StarshipListItemEntity> {
        return type.results.map {
            StarshipListItemEntity(
                MGLT = it.MGLT?:"",
                cargo_capacity = it.cargo_capacity?:"",
                consumables = it.consumables?:"",
                cost_in_credits = it.cost_in_credits?:"",
                crew = it.crew?:"",
                hyperdrive_rating = it.hyperdrive_rating?:"",
                length = it.length?:"",
                manufacturer = it.manufacturer?:"",
                max_atmosphering_speed = it.max_atmosphering_speed?:"",
                model = it.model?:"",
                name = it.name?:"",
                passengers = it.passengers?:"",
                starship_class = it.starship_class?:"",
            )
        }
    }
}