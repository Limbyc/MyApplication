package com.valance.myapplication.ui.fragments

import Coffee
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.valance.myapplication.R
import com.valance.myapplication.databinding.DetailFragmentBinding
import com.valance.myapplication.ui.data.CoffeeData

class DetailFragment : Fragment() {

    lateinit var binding: DetailFragmentBinding
    private var tabLayout: TabLayout? = null


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
                binding.price.text = "$ ${it.price}"
                binding.count.text = "(${it.count})"

                binding.imageCoffee1.setImageResource(it.imageResourceId)
            }
        }

        val textView = binding.appCompatTextView6
        textView.setOnClickListener { onReadMoreClick(textView) }
        setStyledText(textView, R.string.read_more_text)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabLayout = binding.SizeCoffee
        val coffeeSize = listOf("S","M","L")

        for (coffeeSizes in coffeeSize) {
            val tab = tabLayout?.newTab()
            val customTabView = createTabView(coffeeSizes)
            tab?.customView = customTabView
            tab?.tag = coffeeSizes
            tabLayout?.addTab(tab!!)
        }
        tabLayout?.addOnTabSelectedListener(createTabListener())
        tabLayout?.getTabAt(1)?.select()

        binding.appCompatImageView4.setOnClickListener{
            findNavController().navigate(R.id.mainFragment)
        }


    }

    private fun createTabView(text: String): View {
        val customTabView = LayoutInflater.from(context).inflate(R.layout.size_coffee, null) as TextView
        customTabView.text = text
        return customTabView
    }
    private fun setStyledText(textView: TextView, stringResourceId: Int) {
        val context: Context = requireContext()

        val customTypeface = ResourcesCompat.getFont(context, R.font.sora_semibold)

        val spannableString = SpannableString(context.getString(stringResourceId))
        val colorSpan = ForegroundColorSpan(context.resources.getColor(R.color.button))
        val styleSpan = StyleSpan(Typeface.BOLD)

        spannableString.setSpan(colorSpan, spannableString.length - 9, spannableString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(styleSpan, spannableString.length - 9, spannableString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        textView.text = spannableString
    }

    private fun onReadMoreClick(view: View) {
        val textView = view as TextView
        textView.maxLines = Integer.MAX_VALUE
        textView.setText(R.string.additional_text)
    }

    private fun createTabListener(): TabLayout.OnTabSelectedListener {
        return object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val selectedCoffeeType: String? = tab.tag as? String
                selectedCoffeeType?.let {
                }
                updateTabAppearance(tab, R.color.price_detail, R.drawable.size_coffee_press, R.font.sora_regular)
            }


            override fun onTabUnselected(tab: TabLayout.Tab?) {
                updateTabAppearance(tab, R.color.black, R.drawable.size_coffee_bg, R.font.sora_regular)
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

