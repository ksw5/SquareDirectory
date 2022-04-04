package com.example.squaredirectoryproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.squaredirectoryproject.adapters.Adapter
import com.example.squaredirectoryproject.viewmodels.EmployeeViewModel
import com.example.squaredirectoryproject.databinding.FragmentEmployeesBinding


class EmployeesFragment : Fragment() {

    private val viewModel : EmployeeViewModel by activityViewModels()
    lateinit var employeesAdapter: Adapter
    lateinit var recyclerView: RecyclerView
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

        getEmployees()
        //setupRecyclerView()

        /*viewModel.apiResponse?.observe(viewLifecycleOwner) {
            val list = mutableListOf(it)
            employeesAdapter.notifyDataSetChanged()

        }*/


    }

    private fun getEmployees() {
        viewModel.apiResponse.observe(viewLifecycleOwner) {
            recyclerView.apply {
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = Adapter(it)
                adapter?.notifyDataSetChanged()
            }
        }
    }

//    private fun setupRecyclerView() {
//        employeesAdapter = Adapter()
//        binding.recyclerView.apply {
//            layoutManager = LinearLayoutManager(context)
//            adapter = employeesAdapter
//
//
//        }
//    }



}