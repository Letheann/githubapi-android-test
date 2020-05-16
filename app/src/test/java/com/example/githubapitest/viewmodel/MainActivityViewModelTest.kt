package com.example.githubapitest.viewmodel

import com.example.githubapitest.CoroutineTestRule
import com.example.githubapitest.model.Search
import com.example.githubapitest.model.ViewEvents
import com.example.githubapitest.repository.usecases.GetUsers
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.rules.TestRule


class MainActivityViewModelTest {

    private var repo: GetUsers = mock()

    private val users: Search = mock()

    private val viewmodel: MainActivityViewModel = MainActivityViewModel(repo)

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()


    @Test
    fun incrementPage() {
        assertEquals(viewmodel.incrementPage(), "2")
    }

    @Test
    fun resetPage() {
        viewmodel.resetPage()
        assertEquals(viewmodel.returnPage(), 2)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun when_call_api_should_return_value() = runBlocking {

        whenever(repo.execute(any(), any(), any(), any())) doReturn users

        viewmodel.getRepos()

        assertEquals(
            ViewEvents.SuccessGetUsers(
                users
            ), viewmodel.viewState().value
        )
    }
}