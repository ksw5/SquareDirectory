package com.example.squaredirectoryproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.squaredirectoryproject.EmployeesService
import com.example.squaredirectoryproject.model.data.Employees
import kotlinx.coroutines.launch
import retrofit2.Call
import java.lang.Exception

class EmployeeViewModel: ViewModel() {
    private val _apiResponse = MutableLiveData<Call<Employees>>()
    val apiResponse: LiveData<Call<Employees>> = _apiResponse


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