package com.project.lalabib.ican.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.project.lalabib.ican.databinding.ContentDashboardBinding
import com.project.lalabib.ican.databinding.FragmentDashboardBinding
import com.project.lalabib.ican.ui.adapter.FishAdapter
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dashboardBinding = binding.contentDashboard

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[DashboardViewModel::class.java]

        val fishAdapter = FishAdapter()

        //show data
        if (activity != null) {
            showLoading(true)

            viewModel.getFishDashboard().observe(viewLifecycleOwner) { fish ->
                fishAdapter.setFish(fish)
                fishAdapter.notifyDataSetChanged()
                showLoading(false)
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