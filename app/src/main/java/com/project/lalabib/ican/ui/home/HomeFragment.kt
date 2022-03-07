package com.project.lalabib.ican.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.project.lalabib.ican.R
import com.project.lalabib.ican.databinding.ContentHomeBinding
import com.project.lalabib.ican.databinding.FragmentHomeBinding
import com.project.lalabib.ican.ui.adapter.FishAdapter
import com.project.lalabib.ican.viewmodel.ViewModelFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var homeContentBinding: ContentHomeBinding

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeContentBinding = binding.contentHome

        //image slide show
        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel(R.drawable.iklan, ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.iklan2, ScaleTypes.CENTER_CROP))

        val imageSlider = homeContentBinding.imageSlider
        imageSlider.setImageList(imageList)

        //show data
        if (activity != null) {

            val factory = ViewModelFactory.getInstance()
            val viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

            val fishAdapter = FishAdapter()

            viewModel.getFishs().observe(viewLifecycleOwner) { fish ->
                fishAdapter.setFish(fish)
                fishAdapter.notifyDataSetChanged()
            }

            homeContentBinding.apply {
                rvBestSeller.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                rvBestSeller.setHasFixedSize(true)
                rvBestSeller.adapter = fishAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}