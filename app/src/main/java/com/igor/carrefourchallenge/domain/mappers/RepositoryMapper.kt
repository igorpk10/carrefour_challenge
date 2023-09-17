package com.igor.carrefourchallenge.domain.mappers

import com.igor.carrefourchallenge.data.models.RepositoriesResponse
import com.igor.carrefourchallenge.domain.models.Repository

fun List<RepositoriesResponse>.mapToRepositories(): List<Repository> =
    this.filter { it.private == false }.map {
        Repository(
            id = it.id,
            name = it.name,
            url = it.htmlUrl
        )
    }