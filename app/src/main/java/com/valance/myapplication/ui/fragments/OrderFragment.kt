package com.valance.myapplication.ui.fragments

import Coffee
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.valance.myapplication.R
import com.valance.myapplication.databinding.OrderFragmentBinding
import com.valance.myapplication.ui.ViewModel.SharedViewModel
import com.valance.myapplication.ui.data.CoffeeData

class OrderFragment : Fragment() {

    private lateinit var binding: OrderFragmentBinding
    private var tabLayout: TabLayout? = null
    private val sharedViewModel by activityViewModels<SharedViewModel>()
    private var totalPayment: Double = 1.0
    private var counterValue = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = OrderFragmentBinding.inflate(inflater, container, false)
        setupCounter()
        setupCoffeeDetails()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTabLayout()
        setupBackArrow()
        setupStrikeThroughText()
    }

    private fun setupCounter() {
        binding.counter.text = counterValue.toString()

        binding.appCompatImageView10.setOnClickListener {
            if (counterValue > 1) {
                counterValue--
                binding.counter.text = counterValue.toString()
                updateTotalPayment()
                if (counterValue == 1) {
                    binding.appCompatImageView10.setImageResource(R.drawable.minus)
                } else {
                    binding.appCompatImageView10.setImageResource(R.drawable.minus_product)
                }
            }
        }

        binding.appCompatImageView8.setOnClickListener {
            counterValue++
            binding.counter.text = counterValue.toString()
            if (counterValue == 1) {
                binding.appCompatImageView10.setImageResource(R.drawable.minus)
            } else {
                binding.appCompatImageView10.setImageResource(R.drawable.minus_product)
            }
            updateTotalPayment()
        }
    }


    private fun setupCoffeeDetails() {
        val coffeeId: Int? = sharedViewModel.selectedCoffeeId
        coffeeId?.let { it ->
            val selectedCoffee: Coffee? = CoffeeData.getCoffeeById(it)
            selectedCoffee?.let {
                binding.nameCoffee.text = it.name
                binding.description.text = it.description
                binding.cost.text = "$ ${it.price}"
                binding.coffeePhoto.setImageResource(it.imageResourceId)

                updateTotalPayment()
            }
        }
    }

    private fun setupTabLayout() {
        tabLayout = binding.tabLayout2
        val buyTypes = listOf("Deliver", "Pick Up")

        for (buyType in buyTypes) {
            val tab = tabLayout?.newTab()
            val customTabView = createTabView(buyType)
            tab?.customView = customTabView
            tab?.tag = buyType
            tabLayout?.addTab(tab!!)
        }

        tabLayout?.addOnTabSelectedListener(createTabListener())
        tabLayout?.getTabAt(0)?.select()
    }

    private fun setupBackArrow() {
        binding.arrowLeft.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupStrikeThroughText() {
        val paint = Paint()
        paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
        binding.zacurknutyText.paintFlags = paint.flags
    }

    private fun updateTotalPayment() {
        val selectedCoffee = CoffeeData.getCoffeeById(sharedViewModel.selectedCoffeeId ?: return)
        selectedCoffee?.let {
            val totalPayment = (counterValue * it.price) + totalPayment
            binding.totalPayment1.text = "$ ${String.format("%.2f", totalPayment)}"
            binding.totalPayment.text = "$ ${String.format("%.2f", totalPayment)}"
        }
    }

    private fun createTabView(text: String): View {
        val customTabView =
            LayoutInflater.from(context).inflate(R.layout.buy_type, null) as TextView
        customTabView.text = text
        return customTabView
    }

    private fun createTabListener(): TabLayout.OnTabSelectedListener {
        return object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                Log.d("MainFragment", "onTabSelected called")
                val selectedCoffeeType: String? = tab.tag as? String
                selectedCoffeeType?.let { text ->
                    Log.d("MainFragment", "Selected coffee type: $text")
                }
                updateTabAppearance(tab, R.color.white, R.drawable.coffee_types, R.font.sora_semibold)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                updateTabAppearance(tab, R.color.text_mainfragment, R.drawable.buy_type, R.font.sora_regular)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                onTabSelected(tab)
            }
        }
    }

    private fun updateTabAppearance(tab: TabLayout.Tab?, textColorResId: Int, backgroundDrawableResId: Int, fontResourceId: Int) {
        val customTabView = tab?.customView as? TextView
        customTabView?.setTextColor(ContextCompat.getColor(requireContext(), textColorResId))
        customTabView?.background =
            ContextCompat.getDrawable(requireContext(), backgroundDrawableResId)
        customTabView?.typeface = ResourcesCompat.getFont(requireContext(), fontResourceId)
    }
}
