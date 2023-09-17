package com.igor.carrefourchallenge.domain.mappers

import com.igor.carrefourchallenge.data.models.UsersResponse
import com.igor.carrefourchallenge.domain.models.User

fun List<UsersResponse>.mapToUsers(): List<User> {
    return this.map {
        User(
            id = it.id,
            login = it.login,
            avatarUrl = it.avatarUrl,
            url = it.htmlUrl
        )
    }
}