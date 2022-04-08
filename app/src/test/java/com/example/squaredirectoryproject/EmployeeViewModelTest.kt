package com.example.squaredirectoryproject

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.squaredirectoryproject.viewmodels.EmployeeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations


@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class EmployeeViewModelTest {
    lateinit var viewModel: EmployeeViewModel

    @Mock
    lateinit var service: EmployeeApiRequest
    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = EmployeeViewModel()
    }

    @Test
    fun `get all employees test`() {
        runBlocking {
            viewModel.getEmployees()

        }
    }
}