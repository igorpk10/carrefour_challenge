package com.igor.carrefourchallenge.domain.mappers

import com.igor.carrefourchallenge.data.models.UserResponse
import com.igor.carrefourchallenge.domain.models.Repository
import com.igor.carrefourchallenge.domain.models.UserDetails

fun UserResponse.mapToUserDetails(repositories: List<Repository>): UserDetails = UserDetails(
    company = this.company,
    followers = this.followers,
    location = this.location,
    repositories = repositories
)