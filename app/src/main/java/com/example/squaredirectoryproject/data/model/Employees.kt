package com.example.squaredirectoryproject.data.model

import com.example.squaredirectoryproject.data.model.Employee
import kotlinx.serialization.Serializable



data class Employees(
    val employees: List<Employee>
)
