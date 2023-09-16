package com.igor.carrefourchallenge.domain.usecases

import com.igor.carrefourchallenge.data.repositories.UserRepository
import com.igor.carrefourchallenge.di.coroutines.CoroutinesDispatchers
import com.igor.carrefourchallenge.domain.mappers.mapToUsers
import com.igor.carrefourchallenge.domain.models.User
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchUsersUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository,
    private val dispatcher: CoroutinesDispatchers
) : FetchUsersUseCase {
    override suspend fun invoke(): List<User> {
        return withContext(dispatcher.io()){
            userRepository.fetchUsers().mapToUsers()
        }
    }

}