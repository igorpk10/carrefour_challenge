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

    override suspend fun fetchUserDetails(login: String): UserResponse = api.fetchUserDetails(login)

    override suspend fun fetchUserRepository(login: String): List<RepositoriesResponse> =
        api.fetchUserRepository(login)
}