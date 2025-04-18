package com.safi.data.apiservices

import com.safi.response.CharactersApiResponse
import com.safi.response.PlanetApiResponse
import com.safi.response.StarshipApiResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("characters/{page}.json")
    suspend fun fetchCharacters(@Path("page") page : Int) : Response<CharactersApiResponse>

    @GET("starships/")
    suspend fun fetchStarships(@Query("page") page : Int) : Response<StarshipApiResponse>

    @GET("planets/")
    suspend fun fetchPlanets(@Query("page") page : Int) : Response<PlanetApiResponse>

}