package com.valance.myapplication.ui.fragments

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.valance.myapplication.R
import com.valance.myapplication.databinding.MainFragmentBinding
import com.valance.myapplication.utils.ImageUtils

class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val originalBitmap = BitmapFactory.decodeResource(resources, R.drawable.woman)
        val maskDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.mask)

        maskDrawable?.let {
            val maskedBitmap = ImageUtils.applyRoundedCornerMask(originalBitmap, it)
            val myImageView = view.findViewById<ImageView>(R.id.imageView)
            myImageView.setImageBitmap(maskedBitmap)
        }
    }
}
