package com.igor.carrefourchallenge.domain.models

data class UserDetails(
    val id: Int,
    val name: String,
    val avatarUrl: String,
    val repository: String,
    val company: String,
    val followers: Int
)
