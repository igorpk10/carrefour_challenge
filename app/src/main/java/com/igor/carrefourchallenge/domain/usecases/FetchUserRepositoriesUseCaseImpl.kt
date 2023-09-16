package com.igor.carrefourchallenge.domain.usecases

import com.igor.carrefourchallenge.data.repositories.UserRepository
import com.igor.carrefourchallenge.di.coroutines.CoroutinesDispatchers
import com.igor.carrefourchallenge.domain.mappers.mapToRepositories
import com.igor.carrefourchallenge.domain.models.Repository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchUserRepositoriesUseCaseImpl @Inject constructor(
    private val repository: UserRepository,
    private val dispatchers: CoroutinesDispatchers
) : FetchUserRepositoriesUseCase {
    override suspend fun invoke(login: String): List<Repository> {
        return withContext(dispatchers.io()) {
            repository.fetchUserRepository(login).mapToRepositories()
        }
    }
}