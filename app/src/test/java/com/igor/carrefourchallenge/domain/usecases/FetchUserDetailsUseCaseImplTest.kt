package com.igor.carrefourchallenge.domain.usecases

import com.igor.carrefourchallenge.data.repositories.UserRepository
import com.igor.carrefourchallenge.domain.models.UserDetails
import com.igor.carrefourchallenge.factories.buildUserRepositories
import com.igor.carrefourchallenge.factories.buildUserResponse
import com.igor.carrefourchallenge.testconfig.MainCoroutineRule
import com.nhaarman.mockitokotlin2.any
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
class FetchUserDetailsUseCaseImplTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var repository: UserRepository

    lateinit var fetchUserDetailsUseCase: FetchUserDetailsUseCaseImpl


    @Before
    fun setup() {
        fetchUserDetailsUseCase =
            FetchUserDetailsUseCaseImpl(repository, mainCoroutineRule.testDispatcherProvider)
    }

    @Test
    fun `should validate if parse to UserDetails its happen`() = runTest {
        whenever(repository.fetchUserDetails(any())).thenReturn(buildUserResponse())
        whenever(repository.fetchUserRepository(any())).thenReturn(buildUserRepositories())

        val login = "luffy"
        val result = fetchUserDetailsUseCase.invoke(login)

        Assert.assertEquals(UserDetails::class.java, result::class.java)
        assert(result.repositories.isNotEmpty())
    }
}