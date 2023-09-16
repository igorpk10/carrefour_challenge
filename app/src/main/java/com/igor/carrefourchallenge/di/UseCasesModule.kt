package com.igor.carrefourchallenge.di

import com.igor.carrefourchallenge.domain.usecases.FetchUserRepositoriesUseCase
import com.igor.carrefourchallenge.domain.usecases.FetchUserRepositoriesUseCaseImpl
import com.igor.carrefourchallenge.domain.usecases.FetchUserUseCase
import com.igor.carrefourchallenge.domain.usecases.FetchUserUseCaseImpl
import com.igor.carrefourchallenge.domain.usecases.FetchUsersUseCase
import com.igor.carrefourchallenge.domain.usecases.FetchUsersUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCasesModule {

    @Binds
    fun bindFetchUsersUseCase(useCase: FetchUsersUseCaseImpl): FetchUsersUseCase

    @Binds
    fun bindFetchUserUseCase(useCase: FetchUserUseCaseImpl): FetchUserUseCase

    @Binds
    fun bindFetchUserRepositoryUseCase(useCase: FetchUserRepositoriesUseCaseImpl): FetchUserRepositoriesUseCase
}