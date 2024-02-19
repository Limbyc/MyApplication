package com.valance.myapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.valance.myapplication.R
import com.valance.myapplication.databinding.RegistrationFragmentBinding

class StartingFragment : Fragment() {

    private lateinit var binding: RegistrationFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RegistrationFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        StartButton()
    }

    private fun StartButton() {
        binding.StartButton.setOnClickListener {
            findNavController().navigate(R.id.action_startingFragment_to_mainFragment)
        }
    }
}
