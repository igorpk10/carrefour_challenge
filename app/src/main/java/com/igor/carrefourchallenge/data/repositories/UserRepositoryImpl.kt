package com.igor.carrefourchallenge.data.repositories

import com.igor.carrefourchallenge.data.GithubApi
import com.igor.carrefourchallenge.data.models.RepositoriesResponse
import com.igor.carrefourchallenge.data.models.UserResponse
import com.igor.carrefourchallenge.data.models.UsersResponse
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: GithubApi
) : UserRepository {
    override suspend fun fetchUsers(): List<UsersResponse> = api.fetchUsers()

    override suspend fun fetchUserDetails(name: String): UserResponse = api.fetchUserDetails(name)

    override suspend fun fetchUserRepository(name: String): List<RepositoriesResponse> =
        api.fetchUserRepository(name)
}