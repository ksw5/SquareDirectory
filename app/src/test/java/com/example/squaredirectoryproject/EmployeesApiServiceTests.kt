package com.example.squaredirectoryproject

import BaseTest
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class EmployeesApiServiceTests : BaseTest() {
    private lateinit var service: EmployeeApiRequest

    @Before
    fun setup() {
        val url = mockWebServer.url("/")
        service = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create(
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
            ))
            .build()
            .create(EmployeeApiRequest::class.java)

    }

    @Test
    fun `testing the api service`() {

        enqueue("employees.json")
        runBlocking {
            val apiResponse = service.getEmployees()

            assertNotNull(apiResponse)

        }
    }
}