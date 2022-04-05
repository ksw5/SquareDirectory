package com.example.squaredirectoryproject.ui

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
import com.example.squaredirectoryproject.adapters.Adapter
import com.example.squaredirectoryproject.viewmodels.EmployeeViewModel
import com.example.squaredirectoryproject.databinding.FragmentEmployeesBinding



class EmployeesFragment : Fragment() {

    private val viewModel : EmployeeViewModel by activityViewModels()
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
            recyclerView.apply {
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = Adapter(it)
                adapter?.notifyDataSetChanged()
            }
        }
    }

    fun fetchNewEmployees() {
        swipeContainer.setOnRefreshListener {
            swipeContainer.isRefreshing = false
            swipeContainer.setColorSchemeResources(
                R.color.baby_blue,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light)

            viewModel

            Toast.makeText(context, "Page Refreshed", Toast.LENGTH_SHORT).show()
        }

    }

//    private fun loadData() {
//        refreshLayout.setRefreshing(true) // 2
//        BlogHttpClient.INSTANCE.loadBlogArticles(object : BlogArticlesCallback() {
//            fun onSuccess(blogList: List<Blog?>?) {
//                UiThreadStatement.runOnUiThread(Runnable {
//                    refreshLayout.setRefreshing(false) // 3
//                    adapter.submitList(blogList)
//                })
//            }
//
//            fun onError() {
//                UiThreadStatement.runOnUiThread(Runnable {
//                    refreshLayout.setRefreshing(false) // 4
//                    showErrorSnackbar()
//                })
//            }
//        })
//    }




}



