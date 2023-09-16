package com.igor.carrefourchallenge.domain.usecases

import com.igor.carrefourchallenge.data.repositories.UserRepository
import com.igor.carrefourchallenge.di.coroutines.CoroutinesDispatchers
import com.igor.carrefourchallenge.domain.mappers.mapToUserDetails
import com.igor.carrefourchallenge.domain.models.UserDetails
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchUserUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository,
    private val coroutinesDispatchers: CoroutinesDispatchers
) : FetchUserUseCase {
    override suspend fun invoke(login: String): UserDetails {
        return withContext(coroutinesDispatchers.io()) {
            userRepository.fetchUserDetails(login).mapToUserDetails()
        }
    }
}