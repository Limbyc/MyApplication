package com.valance.myapplication.ui.fragments

import ItemOffsetDecoration
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.valance.myapplication.R
import com.valance.myapplication.databinding.MainFragmentBinding
import com.valance.myapplication.ui.adapter.CoffeeAdapter
import com.valance.myapplication.ui.data.Coffee
import com.valance.myapplication.utils.ImageUtils
class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private var tabLayout: TabLayout? = null

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

        val recyclerView: RecyclerView = binding.recyclerViewCoffee
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        val offset = resources.getDimensionPixelOffset(R.dimen.item_offset)
        recyclerView.addItemDecoration(ItemOffsetDecoration(offset))

        val productList = listOf(
            Coffee(4.8, "Cappucino", "with Chocolate", 4.53, R.drawable.coffee1),
            Coffee(4.9, "Cappucino", "with Oat Milk", 3.90, R.drawable.coffee2),
            Coffee(4.5, "Cappucino", "with Oat Milk", 4.50, R.drawable.coffee3),
            Coffee(4.0, "Cappucino", "with Chocolate", 4.20, R.drawable.coffee4)
        )

        recyclerView.adapter = CoffeeAdapter(productList)


        tabLayout = binding.tabLayout
        val coffeeTypes = listOf("Cappuccino", "Machiato", "Latte", "Americano", "Espresso")
        tabLayout?.selectTab(tabLayout?.getTabAt(0))

        tabLayout?.addOnTabSelectedListener(createTabListener())

        for (coffeeType in coffeeTypes) {
            val tab = tabLayout?.newTab()
            val customTabView = createTabView(coffeeType)
            tab?.customView = customTabView
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
            override fun onTabSelected(tab: TabLayout.Tab?) {
                updateTabAppearance(tab, R.color.white, R.drawable.coffee_types, R.font.sora_semibold)
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                updateTabAppearance(tab, R.color.text_mainfragment, R.drawable.type_of_coffee, R.font.sora_regular)
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
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

