package com.safi.entity

import java.io.Serializable

data class PlanetListItemEntity(
    val climate: String,
    val diameter: String,
    val gravity: String,
    val name: String,
    val orbital_period: String,
    val population: String,
    val rotation_period: String,
    val surface_water: String,
    val terrain: String,
) : Serializable