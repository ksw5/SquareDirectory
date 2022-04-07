package com.example.squaredirectoryproject

import android.net.ConnectivityManager
import com.example.squaredirectoryproject.data.model.Employee
import com.example.squaredirectoryproject.data.model.Employees
import com.example.squaredirectoryproject.data.network.ConnectivityCheckingInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.internal.connection.ConnectInterceptor
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET



val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val networkLoggingInterceptor =
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
val retrofit = Retrofit.Builder()
    .client(OkHttpClient.Builder().addInterceptor(networkLoggingInterceptor).build())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface EmployeeApiRequest {
    @GET("employees_malformed.json")
    fun getEmployees() : Call<Employees>
}

object EmployeesService {
    val retrofitService: EmployeeApiRequest by lazy { retrofit.create(EmployeeApiRequest::class.java) }
}