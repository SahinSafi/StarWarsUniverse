package com.safi.entity

import java.io.Serializable

data class CharacterListItemEntity(
    val id: Int,
    val birth_year: String,
    val eye_color: String,
    val gender: String,
    val hair_color: String,
    val height: String,
    val mass: String,
    val name: String,
    val skin_color: String,
) : Serializable