package com.safi.response

data class PlanetApiResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Planet>
)

data class Planet(
    val climate: String?,
    val created: String?,
    val diameter: String?,
    val edited: String?,
    val gravity: String?,
    val name: String?,
    val orbital_period: String?,
    val population: String?,
    val rotation_period: String?,
    val surface_water: String?,
    val terrain: String?,
)