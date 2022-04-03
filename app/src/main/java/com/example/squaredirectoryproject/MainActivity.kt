package com.example.squaredirectoryproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {

    private val viewModel: EmployeeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getEmployees()
    }

    private fun getEmployees() {
        viewModel.apiResponse.observe(this) {
            viewModel.getEmployees()
        }
    }
}