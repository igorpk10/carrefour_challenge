package com.igor.carrefourchallenge.data

import com.igor.carrefourchallenge.data.models.RepositoriesResponse
import com.igor.carrefourchallenge.data.models.UserResponse
import com.igor.carrefourchallenge.data.models.UsersResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    @GET("/users")
    suspend fun fetchUsers(): List<UsersResponse>

    @GET("/users/{login}")
    suspend fun fetchUserDetails(@Path("login") login: String): UserResponse

    @GET("/users/{login}/repos")
    suspend fun fetchUserRepository(@Path("login") login: String): List<RepositoriesResponse>
}