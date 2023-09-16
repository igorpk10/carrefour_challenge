package com.igor.carrefourchallenge.domain.usecases

import com.igor.carrefourchallenge.domain.models.User

interface FetchUsersUseCase {

    suspend operator fun invoke(): List<User>

}