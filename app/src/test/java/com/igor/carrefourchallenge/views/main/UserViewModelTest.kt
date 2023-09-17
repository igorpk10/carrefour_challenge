package com.igor.carrefourchallenge.views.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.igor.carrefourchallenge.domain.mappers.mapToUsers
import com.igor.carrefourchallenge.domain.usecases.FetchUsersUseCase
import com.igor.carrefourchallenge.factories.buildUserResponseList
import com.igor.carrefourchallenge.testconfig.MainCoroutineRule
import com.igor.carrefourchallenge.views.main.states.UserActivityUiState
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
class UserViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule()
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var fetchUsersUseCase: FetchUsersUseCase

    @Mock
    private lateinit var uiStateObserver: Observer<UserActivityUiState>

    private lateinit var userViewModel: UserViewModel

    @Before
    fun setup() {
        userViewModel = UserViewModel(fetchUsersUseCase).apply {
            this.state.observeForever(uiStateObserver)
        }
    }

    @Test
    fun `should notify a success when load users to ui`() = runTest {
        whenever(fetchUsersUseCase.invoke()).thenReturn(buildUserResponseList().mapToUsers())

        userViewModel.getUsers()

        verify(uiStateObserver).onChanged(isA<UserActivityUiState.onSuccess>())
    }

    @Test
    fun `should notify a error when load users to ui`() = runTest {
        whenever(fetchUsersUseCase.invoke()).thenThrow(RuntimeException())

        userViewModel.getUsers()

        verify(uiStateObserver).onChanged(isA<UserActivityUiState.onError>())
    }

    @Test
    fun `should apply filber by name`() = runTest {
        whenever(fetchUsersUseCase.invoke()).thenReturn(buildUserResponseList().mapToUsers())
        userViewModel.getUsers()
        verify(uiStateObserver).onChanged(isA<UserActivityUiState.onSuccess>())


        val query = "String2"
        userViewModel.filterByName(query)

        val result = userViewModel.state.value as UserActivityUiState.onSuccess

        assert(result.users.filter { it.login == query } != null)
    }

}