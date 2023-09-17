package com.igor.carrefourchallenge.domain.usecases

import com.igor.carrefourchallenge.data.repositories.UserRepository
import com.igor.carrefourchallenge.di.coroutines.CoroutinesDispatchers
import com.igor.carrefourchallenge.domain.mappers.mapToRepositories
import com.igor.carrefourchallenge.domain.mappers.mapToUserDetails
import com.igor.carrefourchallenge.domain.models.UserDetails
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchUserDetailsUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository,
    private val coroutinesDispatchers: CoroutinesDispatchers
) : FetchUserDetailsUseCase {
    override suspend fun invoke(login: String): UserDetails {
        return withContext(coroutinesDispatchers.io()) {
            val detailsDeferred = async { userRepository.fetchUserDetails(login) }
            val repositoriesDeffered = async { userRepository.fetchUserRepository(login) }

            val details = detailsDeferred.await()
            val repositories = repositoriesDeffered.await()

            details.mapToUserDetails(repositories.mapToRepositories())
        }
    }
}