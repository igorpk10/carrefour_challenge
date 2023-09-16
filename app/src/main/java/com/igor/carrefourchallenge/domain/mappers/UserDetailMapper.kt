package com.igor.carrefourchallenge.domain.mappers

import com.igor.carrefourchallenge.data.models.UserResponse
import com.igor.carrefourchallenge.domain.models.UserDetails

fun UserResponse.mapToUserDetails(): UserDetails = UserDetails(
    id = this.id,
    name = this.name,
    avatarUrl = this.avatarUrl,
    repository = this.reposUrl,
    company = this.company,
    followers = this.followers
)