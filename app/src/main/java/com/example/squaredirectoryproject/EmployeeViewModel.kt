package com.example.squaredirectoryproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

class EmployeeViewModel: ViewModel() {
    private val _apiResponse = MutableLiveData<Employees>()
    val apiResponse: LiveData<Employees> = _apiResponse


    init {
        getEmployees()
    }

    fun getEmployees() {
        try {
            viewModelScope.launch {
                _apiResponse.value = EmployeesService.retrofitService.getEmployees()
            }
        } catch (e: Exception) {
            "Failure: ${e.message}"
        }
    }
}