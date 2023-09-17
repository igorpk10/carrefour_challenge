package com.igor.carrefourchallenge.domain.usecases

import com.igor.carrefourchallenge.data.repositories.UserRepository
import com.igor.carrefourchallenge.domain.models.User
import com.igor.carrefourchallenge.factories.buildUserResponseList
import com.igor.carrefourchallenge.testconfig.MainCoroutineRule
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FetchUsersUseCaseImplTest {


    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var repository: UserRepository

    lateinit var fetchUserDetailsUseCase: FetchUsersUseCaseImpl

    @Before
    fun setup() {
        fetchUserDetailsUseCase = FetchUsersUseCaseImpl(repository, mainCoroutineRule.testDispatcherProvider)
    }


    @Test
    fun `should return a list of users`() = runTest {
        whenever(repository.fetchUsers()).thenReturn(buildUserResponseList())

        val response = fetchUserDetailsUseCase()

        val user = response.firstOrNull()

        assert(user != null)
        Assert.assertEquals(user!!::class.java, User::class.java)
    }


}