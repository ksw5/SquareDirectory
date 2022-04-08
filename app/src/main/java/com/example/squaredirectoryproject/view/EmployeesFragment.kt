package com.example.squaredirectoryproject.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.squaredirectoryproject.R
import com.example.squaredirectoryproject.view.adapters.EmployeeAdapter
import com.example.squaredirectoryproject.viewmodel.EmployeeViewModel
import com.example.squaredirectoryproject.databinding.FragmentEmployeesBinding
import com.example.squaredirectoryproject.model.data.Employees
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EmployeesFragment : Fragment() {

    private val viewModel: EmployeeViewModel by activityViewModels()
    lateinit var recyclerView: RecyclerView
    lateinit var swipeContainer: SwipeRefreshLayout
    private var _binding: FragmentEmployeesBinding? = null
    val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEmployeesBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        swipeContainer = binding.swipeContainer

        getEmployees()
        fetchNewEmployees()

    }


    private fun getEmployees() {
        viewModel.apiResponse.observe(viewLifecycleOwner) {
            it.clone().enqueue(object : Callback<Employees> {
                override fun onResponse(call: Call<Employees>, response: Response<Employees>) {
                    val employees = response.body()?.employees
                    // if the json is empty
                    if (employees.isNullOrEmpty()) {
                        binding.emptyList.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.INVISIBLE
                    }else if (employees != null) {
                        for (i in 0 until employees.size - 1){
                            if (response.isSuccessful) {
                                //use the uuid to decide if list is malformed
                                if (employees[i].uuid == null) {
                                    binding.emptyList.visibility = View.VISIBLE
                                    binding.recyclerView.visibility = View.INVISIBLE
                                } else {
                                    recyclerView.apply {
                                        visibility = View.VISIBLE
                                        binding.emptyList.visibility = View.INVISIBLE
                                        recyclerView.layoutManager = LinearLayoutManager(context)
                                        adapter = response.body()?.let { EmployeeAdapter(it) }
                                        recyclerView.adapter = adapter
                                    }

                                }
                            }
                        }
                    }

                }

                override fun onFailure(call: Call<Employees>, t: Throwable) {
                    // hide the recyclerview and display textview on failure
                    binding.emptyList.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.INVISIBLE
                    Toast.makeText(
                        context,
                        "Please check your network connection",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }
    }


    fun fetchNewEmployees() {
        swipeContainer.setOnRefreshListener {
            swipeContainer.isRefreshing = false
            swipeContainer.setColorSchemeResources(
                R.color.baby_blue,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
            )
            getEmployees()
            Toast.makeText(context, "Page Refreshed", Toast.LENGTH_SHORT).show()
        }

    }
}





