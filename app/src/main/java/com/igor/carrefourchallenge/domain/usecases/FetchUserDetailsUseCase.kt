package com.igor.carrefourchallenge.domain.usecases

import com.igor.carrefourchallenge.domain.models.UserDetails

interface FetchUserDetailsUseCase {

    suspend operator fun invoke(login: String): UserDetails

}