package com.igor.carrefourchallenge.views.main.states

import com.igor.carrefourchallenge.domain.models.User

sealed class UserActivityUiState {
    object onLoading : UserActivityUiState()
    class onSuccess(val users: List<User>) : UserActivityUiState()

    class onError(val ex: String) : UserActivityUiState()

    class onSearchError(val error: String): UserActivityUiState()
}
