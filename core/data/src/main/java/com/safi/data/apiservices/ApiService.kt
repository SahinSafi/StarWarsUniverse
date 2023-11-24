package com.safi.data.apiservices

import com.safi.response.CharactersApiResponse
import com.safi.response.StarshipApiResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("people/")
    suspend fun fetchCharacters(@Query("page") page : Int) : Response<CharactersApiResponse>

    @GET("starships/")
    suspend fun fetchStarships(@Query("page") page : Int) : Response<StarshipApiResponse>
}