package com.project.lalabib.ican.ui.dashboard

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.project.lalabib.ican.R
import com.project.lalabib.ican.databinding.ContentDashboardBinding
import com.project.lalabib.ican.databinding.FragmentDashboardBinding
import com.project.lalabib.ican.ui.adapter.FishAdapter
import com.project.lalabib.ican.utils.Status
import com.project.lalabib.ican.viewmodel.ViewModelFactory

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private lateinit var dashboardBinding: ContentDashboardBinding

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dashboardBinding = binding.contentDashboard

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[DashboardViewModel::class.java]

        val fishAdapter = FishAdapter()

        //show data
        if (activity != null) {
            showLoading(true)

            viewModel.getFishDashboard().observe(viewLifecycleOwner) { fish ->
                if (fish != null) {
                    when (fish.status) {
                        Status.LOADING -> {
                            showLoading(true)
                        }
                        Status.SUCCESS -> {
                            showLoading(false)
                            fish.data?.let { fishAdapter.setFish(it) }
                            fishAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            showLoading(false)
                            Toast.makeText(context, R.string.error_message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

            dashboardBinding.apply {
                rvDashboard.layoutManager = GridLayoutManager(context,2)
                rvDashboard.setHasFixedSize(true)
                rvDashboard.adapter = fishAdapter
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        dashboardBinding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}