package com.safi.data.di

import com.safi.data.apiservices.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideMapApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)
}