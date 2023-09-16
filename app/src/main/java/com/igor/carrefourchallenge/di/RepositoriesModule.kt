package com.igor.carrefourchallenge.di

import com.igor.carrefourchallenge.data.repositories.UserRepository
import com.igor.carrefourchallenge.data.repositories.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModule {

    @Binds
    fun bindUserRepository(repositoryImpl: UserRepositoryImpl): UserRepository

}