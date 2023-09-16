package com.igor.carrefourchallenge.data.repositories

import com.igor.carrefourchallenge.data.models.RepositoriesResponse
import com.igor.carrefourchallenge.data.models.UserResponse
import com.igor.carrefourchallenge.data.models.UsersResponse
import retrofit2.http.Path

interface UserRepository {
    suspend fun fetchUsers(): List<UsersResponse>

    suspend fun fetchUserDetails(@Path("name") name: String): UserResponse

    suspend fun fetchUserRepository(@Path("name") name: String): List<RepositoriesResponse>
}