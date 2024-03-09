package com.valance.myapplication.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.valance.myapplication.R
import com.valance.myapplication.databinding.OrderFragmentBinding

class OrderFragment: Fragment() {

    private lateinit var binding: OrderFragmentBinding
    private var tabLayout: TabLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = OrderFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabLayout = binding.tabLayout2
        val buyTypes = listOf("Deliver","Pick Up")

        for (buyType in buyTypes) {
            val tab = tabLayout?.newTab()
            val customTabView = createTabView(buyType)
            tab?.customView = customTabView
            tab?.tag = buyType
            tabLayout?.addTab(tab!!)
        }

        tabLayout?.addOnTabSelectedListener(createTabListener())
    }

    private fun createTabView(text: String): View {
        val customTabView = LayoutInflater.from(context).inflate(R.layout.buy_type, null) as TextView
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
        customTabView?.background = ContextCompat.getDrawable(requireContext(), backgroundDrawableResId)
        customTabView?.typeface = ResourcesCompat.getFont(requireContext(), fontResourceId)
    }
}