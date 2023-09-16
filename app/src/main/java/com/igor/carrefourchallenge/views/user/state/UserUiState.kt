package com.igor.carrefourchallenge.views.user.state

import com.igor.carrefourchallenge.domain.models.Repository
import com.igor.carrefourchallenge.domain.models.UserDetails

sealed class UserUiState {

    object onLoading : UserUiState()
    class onSuccessGetUser(user: UserDetails) : UserUiState()

    class onSuccessGetRepositories(repositories: List<Repository>): UserUiState()
    class onError(handleError: String) : UserUiState()

}
