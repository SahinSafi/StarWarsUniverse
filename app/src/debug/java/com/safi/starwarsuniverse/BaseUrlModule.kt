package com.safi.starwarsuniverse

import com.safi.data.di.StarWarsBaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class BaseUrlModule{
    @Provides
    @StarWarsBaseUrl
    fun provideBaseUrl():String = "https://swapi.dev/api/"

}

