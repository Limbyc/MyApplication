package com.valance.myapplication.ui.fragments

import LickedSharedPreferences
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.valance.myapplication.R
import com.valance.myapplication.databinding.LikeFragmentBinding
import com.valance.myapplication.ui.ViewModel.SharedViewModel
import com.valance.myapplication.ui.adapter.CoffeeAdapter
import com.valance.myapplication.ui.data.CoffeeData

class LikeFragment : Fragment() {

    private lateinit var binding: LikeFragmentBinding
    private val sharedViewModel by activityViewModels<SharedViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LikeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val likedCoffeeIds = LickedSharedPreferences(requireContext()).getLikedCoffeeIds()

        if (likedCoffeeIds.isNotEmpty()) {
            binding.recyclerViewCoffee.visibility = View.VISIBLE
            binding.UDontHaveLikes.visibility = View.GONE
            val likedCoffeeList = likedCoffeeIds.mapNotNull { it.toIntOrNull()
                ?.let { it1 -> CoffeeData.getCoffeeById(it1) } }

            val recyclerView: RecyclerView = binding.recyclerViewCoffee
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

            val adapter = CoffeeAdapter(likedCoffeeList) { clickedCoffee ->
                sharedViewModel.selectedCoffeeId = clickedCoffee.id
                findNavController().navigate(R.id.detailFragment)
            }
            recyclerView.adapter = adapter
        } else {
            binding.UDontHaveLikes.visibility = View.VISIBLE
            binding.recyclerViewCoffee.visibility = View.GONE
        }
    }
}

