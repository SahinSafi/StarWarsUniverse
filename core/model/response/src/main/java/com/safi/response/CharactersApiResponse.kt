package com.safi.response

data class CharactersApiResponse(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: List<Result>?
)

data class Result(
    val birth_year: String?,
    val created: String?,
    val edited: String?,
    val eye_color: String?,
    val gender: String?,
    val hair_color: String?,
    val height: String?,
    val homeworld: String?,
    val mass: String?,
    val name: String?,
    val skin_color: String?,
)