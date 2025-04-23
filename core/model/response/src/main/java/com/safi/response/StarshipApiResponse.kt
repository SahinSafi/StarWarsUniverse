package com.safi.response

data class StarshipApiResponse(
    val page : Int?,
    val results: List<Starship>
)

data class Starship(
    val MGLT: String?,
    val cargo_capacity: String?,
    val consumables: String?,
    val cost_in_credits: String?,
    val crew: String?,
    val hyperdrive_rating: String?,
    val length: String?,
    val manufacturer: String?,
    val max_atmosphering_speed: String?,
    val model: String?,
    val name: String?,
    val passengers: String?,
    val starship_class: String?,
    val url: String?
)