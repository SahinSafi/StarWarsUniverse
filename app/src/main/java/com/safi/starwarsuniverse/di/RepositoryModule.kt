package com.safi.starwarsuniverse.di

import com.safi.data.repoimpl.RepoImpl
import com.safi.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindRepository(repoImpl: RepoImpl): Repository

}