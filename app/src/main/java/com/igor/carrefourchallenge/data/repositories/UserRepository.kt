package com.igor.carrefourchallenge.data.repositories

import com.igor.carrefourchallenge.data.models.RepositoriesResponse
import com.igor.carrefourchallenge.data.models.UserResponse
import com.igor.carrefourchallenge.data.models.UsersResponse
import retrofit2.http.Path

interface UserRepository {
    suspend fun fetchUsers(): List<UsersResponse>

    suspend fun fetchUserDetails(@Path("name") login: String): UserResponse

    suspend fun fetchUserRepository(@Path("name") login: String): List<RepositoriesResponse>
}