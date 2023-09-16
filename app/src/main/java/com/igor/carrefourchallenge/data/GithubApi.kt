package com.igor.carrefourchallenge.data

import com.igor.carrefourchallenge.data.models.RepositoriesResponse
import com.igor.carrefourchallenge.data.models.UserResponse
import com.igor.carrefourchallenge.data.models.UsersResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    @GET("/users")
    fun fetchUsers(): List<UsersResponse>

    @GET("/users/{name}")
    fun fetchUserDetails(@Path("name") name: String): UserResponse

    @GET("/users/{name}/repos")
    fun fetchUserRepositories(@Path("name") name: String): List<RepositoriesResponse>
}