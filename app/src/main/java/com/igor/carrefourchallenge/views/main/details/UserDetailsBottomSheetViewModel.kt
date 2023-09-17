package com.igor.carrefourchallenge.views.main.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igor.carrefourchallenge.domain.extensions.handleError
import com.igor.carrefourchallenge.domain.usecases.FetchUserDetailsUseCase
import com.igor.carrefourchallenge.views.main.states.UserDetailsBottomSheetUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsBottomSheetViewModel @Inject constructor(
    private val fetchUserDetailsUseCase: FetchUserDetailsUseCase,
) : ViewModel() {

    private val _uiState = MutableLiveData<UserDetailsBottomSheetUiState>()
    val uiState: LiveData<UserDetailsBottomSheetUiState> get() = _uiState

    fun getUserDetails(login: String) = viewModelScope.launch {
        _uiState.value = UserDetailsBottomSheetUiState.onLoading
        try {
            val user = fetchUserDetailsUseCase(login)
            _uiState.value = UserDetailsBottomSheetUiState.onSuccess(user)
        } catch (ex: Throwable) {
            _uiState.value = UserDetailsBottomSheetUiState.onError(ex.handleError())
        }
    }

}