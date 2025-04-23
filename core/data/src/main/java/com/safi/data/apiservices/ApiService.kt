package com.safi.data.apiservices

import com.safi.response.CharactersApiResponse
import com.safi.response.PlanetApiResponse
import com.safi.response.StarshipApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("characters/{page}.json")
    suspend fun fetchCharacters(@Path("page") page : Int) : Response<CharactersApiResponse>

    @GET("starship/{page}.json")
    suspend fun fetchStarships(@Path("page") page : Int) : Response<StarshipApiResponse>

    @GET("planet/{page}.json")
    suspend fun fetchPlanets(@Path("page") page : Int) : Response<PlanetApiResponse>

}