package com.igor.carrefourchallenge.views.user.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igor.carrefourchallenge.domain.extensions.handleError
import com.igor.carrefourchallenge.domain.usecases.FetchUserRepositoriesUseCase
import com.igor.carrefourchallenge.domain.usecases.FetchUserUseCase
import com.igor.carrefourchallenge.views.user.state.UserUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val fetchUserUseCase: FetchUserUseCase,
    private val fetchUserRepositoriesUseCase: FetchUserRepositoriesUseCase
) : ViewModel() {

    private val _state = MutableLiveData<UserUiState>()
    val state: LiveData<UserUiState> get() = _state

    fun getUserByLogin(login: String) = viewModelScope.launch {
        try {
            val user = fetchUserUseCase(login)
            _state.value = UserUiState.onSuccessGetUser(user)
        } catch (ex: Throwable) {
            _state.value = UserUiState.onError(ex.handleError())
        }
    }

    fun getUserRepository(login: String) = viewModelScope.launch {
        try {
            val repositories = fetchUserRepositoriesUseCase(login)
            _state.value = UserUiState.onSuccessGetRepositories(repositories)
        } catch (ex: Throwable) {
            _state.value = UserUiState.onError(ex.handleError())
        }
    }


}