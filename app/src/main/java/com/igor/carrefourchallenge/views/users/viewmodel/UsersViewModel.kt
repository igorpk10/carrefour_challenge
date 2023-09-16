package com.igor.carrefourchallenge.views.users.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igor.carrefourchallenge.domain.extensions.handleError
import com.igor.carrefourchallenge.domain.usecases.FetchUsersUseCase
import com.igor.carrefourchallenge.views.users.states.UsersFragmentUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val fetchUsersUseCase: FetchUsersUseCase
) : ViewModel() {

    private val _state = MutableLiveData<UsersFragmentUiState>()
    val state: LiveData<UsersFragmentUiState> get() = _state

    fun getUsers() = viewModelScope.launch {
        try {
            val users = fetchUsersUseCase()
            _state.value = UsersFragmentUiState.onSuccess(users)
        } catch (ex: Throwable) {
            _state.value = UsersFragmentUiState.onError(ex.handleError())
        }
    }
}