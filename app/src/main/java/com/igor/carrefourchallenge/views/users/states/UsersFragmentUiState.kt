package com.igor.carrefourchallenge.views.users.states

import com.igor.carrefourchallenge.domain.models.User

sealed class UsersFragmentUiState {
    object onLoading : UsersFragmentUiState()
    class onSuccess(users: List<User>) : UsersFragmentUiState()
    class onError(ex: String) : UsersFragmentUiState()
}
