package com.valance.myapplication.ui.fragments

import Coffee
import ItemOffsetDecoration
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.valance.myapplication.R
import com.valance.myapplication.databinding.MainFragmentBinding
import com.valance.myapplication.ui.adapter.CoffeeAdapter
import com.valance.myapplication.ui.data.CoffeeData
import com.valance.myapplication.utils.ImageUtils
class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private var tabLayout: TabLayout? = null
    private lateinit var adapter: CoffeeAdapter
    private var originalList: List<Coffee> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productList = CoffeeData.productList
        originalList = productList

        val shuffledList = productList.shuffled()
        val recyclerView: RecyclerView = binding.recyclerViewCoffee
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        adapter = CoffeeAdapter(shuffledList) { selectedCoffee ->
            val coffeeId = selectedCoffee.id

            val bundle = Bundle().apply {
                putInt("selectedCoffeeId", coffeeId)
                putInt("selectedCoffeeImageResId", selectedCoffee.imageResourceId)
            }

            findNavController().navigate(
                R.id.detailFragment,
                bundle
            )
        }
        recyclerView.adapter = adapter

        val offset = resources.getDimensionPixelOffset(R.dimen.item_offset)
        recyclerView.addItemDecoration(ItemOffsetDecoration(offset))


        tabLayout = binding.tabLayout
        val coffeeTypes = listOf("Cappuccino", "Machiato", "Latte", "Americano", "Espresso")

        for (coffeeType in coffeeTypes) {
            val tab = tabLayout?.newTab()
            val customTabView = createTabView(coffeeType)
            tab?.customView = customTabView
            tab?.tag = coffeeType
            tabLayout?.addTab(tab!!)
        }

        val originalBitmap = BitmapFactory.decodeResource(resources, R.drawable.woman)
        val maskDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.mask)

        maskDrawable?.let {
            val maskedBitmap = ImageUtils.applyRoundedCornerMask(originalBitmap, it)
            val myImageView = view.findViewById<ImageView>(R.id.imageView)
            myImageView.setImageBitmap(maskedBitmap)
        }

        tabLayout?.addOnTabSelectedListener(createTabListener())
    }

    private fun createTabView(text: String): View {
        val customTabView = LayoutInflater.from(context).inflate(R.layout.type_of_coffee, null) as TextView
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
                    filterAndDisplayCoffee(text)
                }
                updateTabAppearance(tab, R.color.white, R.drawable.coffee_types, R.font.sora_semibold)
            }


            override fun onTabUnselected(tab: TabLayout.Tab?) {
                updateTabAppearance(tab, R.color.text_mainfragment, R.drawable.type_of_coffee, R.font.sora_regular)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                onTabSelected(tab)
            }
        }
    }
    private fun filterAndDisplayCoffee(coffeeType: String) {
        Log.d("MainFragment", "Filtering by type: $coffeeType")

        val filteredList = originalList.filter { it.type == coffeeType }
        val sortedList = filteredList + (originalList - filteredList.toSet())

        Log.d("MainFragment", "Filtered list size: ${filteredList.size}")
        Log.d("MainFragment", "Sorted list size: ${sortedList.size}")

        adapter.updateList(sortedList)
    }

    private fun updateTabAppearance(tab: TabLayout.Tab?, textColorResId: Int, backgroundDrawableResId: Int, fontResourceId: Int) {
        val customTabView = tab?.customView as? TextView
        customTabView?.setTextColor(ContextCompat.getColor(requireContext(), textColorResId))
        customTabView?.background = ContextCompat.getDrawable(requireContext(), backgroundDrawableResId)
        customTabView?.typeface = ResourcesCompat.getFont(requireContext(), fontResourceId)
    }
}

