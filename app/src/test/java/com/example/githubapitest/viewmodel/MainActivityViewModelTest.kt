package com.example.githubapitest.viewmodel

import com.example.githubapitest.model.Search
import com.example.githubapitest.model.ViewEvents
import com.example.githubapitest.repository.usecases.GetUsers
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.launch
import org.junit.Test


class MainActivityViewModelTest {

    private var repo: GetUsers = mock()

    private val users: Search = mock()

    private val viewmodel: MainActivityViewModel = MainActivityViewModel(repo)

    @Test
    fun incrementPage() {
        assertEquals(viewmodel.incrementPage(), "2")
    }

    @Test
    fun when_call_api_should_return_value() {

        whenever(repo.execute(any(), any(), any(), any())) doReturn users

        viewmodel.getRepos()

        assertEquals(ViewEvents.SuccessGetUsers(users.items), viewmodel.viewState.value)
    }
}