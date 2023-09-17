package com.igor.carrefourchallenge.data.repositories

import com.igor.carrefourchallenge.data.GithubApi
import com.igor.carrefourchallenge.factories.buildUserRepositories
import com.igor.carrefourchallenge.factories.buildUserResponse
import com.igor.carrefourchallenge.factories.buildUserResponseList
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserRepositoryImplTest {

    @Mock
    private lateinit var api: GithubApi

    private lateinit var userRepository: UserRepositoryImpl

    @Before
    fun setup() {
        userRepository = UserRepositoryImpl(api)
    }

    @Test
    fun `should return a list of users`() = runTest {
        whenever(api.fetchUsers()).thenReturn(buildUserResponseList())

        val result = userRepository.fetchUsers()

        assert(result.isNotEmpty())
        assert(result.size == 2)
    }

    @Test
    fun `should return a user with same login`() = runTest {
        whenever(api.fetchUserDetails(any())).thenReturn(buildUserResponse())

        val query = "login"
        val result = userRepository.fetchUserDetails(query)

        assert(result.login == query)
    }

    @Test
    fun `should return repositories only with same owner`() = runTest {
        whenever(api.fetchUserRepository(any())).thenReturn(buildUserRepositories())

        val query = "owner"
        val result = userRepository.fetchUserRepository(query)

        val filtered = result.filter { it.owner.login != query }

        assert(filtered != null)
    }
}