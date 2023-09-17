package com.igor.carrefourchallenge.views.main.states

import com.igor.carrefourchallenge.domain.models.UserDetails

sealed class UserDetailsBottomSheetUiState{
    object onLoading : UserDetailsBottomSheetUiState()

    class onSuccess(val user: UserDetails) : UserDetailsBottomSheetUiState()

    class onError(val ex: String) : UserDetailsBottomSheetUiState()
}
