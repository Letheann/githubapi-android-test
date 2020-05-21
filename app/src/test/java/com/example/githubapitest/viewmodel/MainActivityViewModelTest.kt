package com.example.githubapitest.viewmodel

import com.example.githubapitest.utils.CoroutineTestRule
import com.example.githubapitest.model.users.Search
import com.example.githubapitest.model.events.ViewEvents
import com.example.githubapitest.repository.usecases.GetUsers
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.githubapitest.dao.AppDatabase
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineScope
import org.apache.tools.ant.taskdefs.Execute.launch
import org.checkerframework.checker.guieffect.qual.UI
import org.junit.rules.TestRule
import kotlin.coroutines.CoroutineContext


class MainActivityViewModelTest {

    private var repo: GetUsers = mock()

    private var dao: AppDatabase = mock()

    private val users: Search = mock()

    private val viewModel: MainActivityViewModel = MainActivityViewModel(repo, dao)

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()


    @Test
    fun incrementPage() {
        assertEquals(viewModel.incrementPage(), "2")
    }

    @Test
    fun resetPage() {
        viewModel.resetPage()
        assertEquals(viewModel.returnPage(), 2)
    }


    @Test
    fun when_call_api_should_return_value() = runBlocking {

        whenever(repo.execute(any(), any(), any(), any())) doReturn users

        viewModel.getRepos()

        assertEquals(
            users.items?.let {
                ViewEvents.SuccessGetUsers(
                    it
                )
            }, viewModel.viewState().value
        )
    }
}





