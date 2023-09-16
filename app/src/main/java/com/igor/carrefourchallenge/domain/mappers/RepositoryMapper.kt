package com.igor.carrefourchallenge.domain.mappers

import com.igor.carrefourchallenge.data.models.RepositoriesResponse
import com.igor.carrefourchallenge.domain.models.Repository

fun List<RepositoriesResponse>.mapToRepositories(): List<Repository> = this.map {
    Repository(
        id = it.id,
        name = it.name,
        private = it.private
    )
}