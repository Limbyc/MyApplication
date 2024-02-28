package com.valance.myapplication.ui.fragments

import Coffee
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.valance.myapplication.R
import com.valance.myapplication.databinding.DetailFragmentBinding
import com.valance.myapplication.ui.data.CoffeeData

class DetailFragment : Fragment() {

    lateinit var binding: DetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailFragmentBinding.inflate(inflater, container, false)

        val coffeeId: Int? = arguments?.getInt("selectedCoffeeId")
        coffeeId?.let {
            val selectedCoffee: Coffee? = CoffeeData.getCoffeeById(it)
            selectedCoffee?.let {
                binding.nameCoffee.text = it.name
                binding.rating.text = "${it.rating}"
                binding.description.text = it.description
                binding.price.text = it.price.toString()

                binding.imageCoffee1.setImageResource(it.imageResourceId)
            }
        }

        return binding.root
    }
}

