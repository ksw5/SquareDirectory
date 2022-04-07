package com.example.squaredirectoryproject.viewmodels

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class EmployeeViewModelTest {
    private lateinit var viewModel: EmployeeViewModel

    @Before
    fun setup() {
        viewModel = EmployeeViewModel()
    }

    @Test
    fun `get all employees`(){
        viewModel.getEmployees()
    }
}