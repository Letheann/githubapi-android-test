package com.example.githubapitest.viewmodel

import com.example.githubapitest.model.ViewEvents
import com.example.githubapitest.repository.usecases.GetUsers
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import org.junit.Test


class MainActivityViewModelTest {

    private var getUseCase: GetUsers = mock()

    private val users: List<Users>? = listOf()

    private val viewmodel: MainActivityViewModel = MainActivityViewModel(getUseCase)

    @Test
    fun incrementPage() {
        assertEquals(viewmodel.incrementPage(), "2")
    }

    @Test
    fun when_call_api_should_return_value() {
        whenever(getUseCase.execute("Letheann","2")) doReturn users

        viewmodel.getRepos()

        assertEquals(ViewEvents.SuccessGetUsers(users), viewmodel.viewState.value)
    }
}