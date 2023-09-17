package com.igor.carrefourchallenge.domain.models

data class UserDetails(
    val company: String?,
    val followers: Int,
    val location: String?,
    val repositories: List<Repository>
)
