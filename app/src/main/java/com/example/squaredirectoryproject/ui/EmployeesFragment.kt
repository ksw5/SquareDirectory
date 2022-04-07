package com.example.squaredirectoryproject.ui

import android.net.*
import android.os.Bundle
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
import com.example.squaredirectoryproject.ui.adapters.Adapter
import com.example.squaredirectoryproject.viewmodels.EmployeeViewModel
import com.example.squaredirectoryproject.databinding.FragmentEmployeesBinding

import android.util.Log
import com.example.squaredirectoryproject.data.model.Employee
import com.example.squaredirectoryproject.data.model.Employees
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
                    if (response.isSuccessful) {
                        val employees = response.body()?.employees
                        if (employees != null) {
                            for (element in employees) {
                                if (element == " ")
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<Employees>, t: Throwable) {
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
            viewModel.getEmployees()
            Toast.makeText(context, "Page Refreshed", Toast.LENGTH_SHORT).show()
        }

    }

}



