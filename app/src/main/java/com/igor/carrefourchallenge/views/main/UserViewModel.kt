package com.igor.carrefourchallenge.views.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igor.carrefourchallenge.domain.extensions.handleError
import com.igor.carrefourchallenge.domain.models.User
import com.igor.carrefourchallenge.domain.usecases.FetchUsersUseCase
import com.igor.carrefourchallenge.views.main.states.UserActivityUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val fetchUsersUseCase: FetchUsersUseCase
) : ViewModel() {

    private val _state = MutableLiveData<UserActivityUiState>()
    val state: LiveData<UserActivityUiState> get() = _state

    private var users: List<User> = listOf()
    
    fun getUsers() = viewModelScope.launch {
        _state.value = UserActivityUiState.onLoading
        try {
            users = fetchUsersUseCase()
            _state.value = UserActivityUiState.onSuccess(users)
        } catch (ex: Throwable) {
            _state.value = UserActivityUiState.onError(ex.handleError())
        }
    }

    fun filterByName(text: String) {
        if (text.isNotBlank()) {
            val result = users.filter { it.login.contains(text) }

            if(result.isNullOrEmpty()){
                _state.value = UserActivityUiState.onSearchError("Não encontramos nenhum usuário")
            }else{
                _state.value = UserActivityUiState.onSuccess(result)
            }
        } else {
            _state.value = UserActivityUiState.onSuccess(users)
        }
    }

}