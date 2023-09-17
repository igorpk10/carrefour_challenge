package com.igor.carrefourchallenge.views.main.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.igor.carrefourchallenge.domain.mappers.mapToRepositories
import com.igor.carrefourchallenge.domain.mappers.mapToUserDetails
import com.igor.carrefourchallenge.domain.usecases.FetchUserDetailsUseCase
import com.igor.carrefourchallenge.factories.buildUserRepositories
import com.igor.carrefourchallenge.factories.buildUserResponse
import com.igor.carrefourchallenge.testconfig.MainCoroutineRule
import com.igor.carrefourchallenge.views.main.states.UserDetailsBottomSheetUiState
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.isA
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserDetailsBottomSheetViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule()
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var fetchUserDetailsUseCase: FetchUserDetailsUseCase

    @Mock
    private lateinit var uiStateObserver: Observer<UserDetailsBottomSheetUiState>

    private lateinit var viewmodel: UserDetailsBottomSheetViewModel

    @Before
    fun setup() {
        viewmodel = UserDetailsBottomSheetViewModel(fetchUserDetailsUseCase).apply {
            this.uiState.observeForever(uiStateObserver)
        }
    }

    @Test
    fun `should return a success state when user details is fetched`() = runTest {
        val repositories = buildUserRepositories()
        whenever(fetchUserDetailsUseCase.invoke(any())).thenReturn(
            buildUserResponse().mapToUserDetails(
                repositories.mapToRepositories()
            )
        )

        viewmodel.getUserDetails("")

        verify(uiStateObserver).onChanged(isA<UserDetailsBottomSheetUiState.onSuccess>())
    }

    @Test
    fun `should return a error state when user details is fetched`() = runTest {
        val repositories = buildUserRepositories()
        whenever(fetchUserDetailsUseCase.invoke(any())).thenThrow(RuntimeException())

        viewmodel.getUserDetails("")

        verify(uiStateObserver).onChanged(isA<UserDetailsBottomSheetUiState.onError>())
    }
}