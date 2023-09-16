package com.igor.carrefourchallenge.domain.usecases

import com.igor.carrefourchallenge.domain.models.Repository

interface FetchUserRepositoriesUseCase {

    suspend operator fun invoke(login: String): List<Repository>

}