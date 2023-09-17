package com.igor.carrefourchallenge.di

import com.igor.carrefourchallenge.domain.usecases.FetchUserDetailsUseCase
import com.igor.carrefourchallenge.domain.usecases.FetchUserDetailsUseCaseImpl
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
    fun bindFetchUserUseCase(useCase: FetchUserDetailsUseCaseImpl): FetchUserDetailsUseCase
}