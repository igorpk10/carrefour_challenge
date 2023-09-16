package com.igor.carrefourchallenge.domain.usecases

import com.igor.carrefourchallenge.domain.models.UserDetails

interface FetchUserUseCase {

    suspend fun invoke(login: String): UserDetails

}