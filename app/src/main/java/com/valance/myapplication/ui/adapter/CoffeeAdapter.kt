package com.valance.myapplication.ui.adapter

import Coffee
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.valance.myapplication.R
import com.valance.myapplication.utils.ImageUtils

class CoffeeAdapter(
    private var productList: List<Coffee?>,
    private val onItemClick: (coffee: Coffee) -> Unit
) : RecyclerView.Adapter<CoffeeAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.coffee_recycler_view_element, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        if (product != null) {
            holder.bind(product)
        }

        holder.itemView.findViewById<View>(R.id.add_coffee).setOnClickListener {
            if (product != null) {
                onItemClick.invoke(product)
            }
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    // Добавьте метод для обновления списка
    fun updateList(newList: List<Coffee>) {
        productList = newList
        notifyDataSetChanged()
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageViewProduct: ImageView = itemView.findViewById(R.id.imageCoffee)
        private val textViewName: TextView = itemView.findViewById(R.id.name_coffee)
        private val textViewDescription: TextView = itemView.findViewById(R.id.description)
        private val textViewPrice: TextView = itemView.findViewById(R.id.cost)
        private val rating: TextView = itemView.findViewById(R.id.rating)

        fun bind(product: Coffee) {
            textViewName.text = product.name
            textViewDescription.text = product.description
            textViewPrice.text = "$ ${product.price}"
            rating.text = "${product.rating}"

            val originalBitmap = BitmapFactory.decodeResource(itemView.resources, product.imageResourceId)
            val maskDrawable = ContextCompat.getDrawable(itemView.context, R.drawable.mask)

            if (originalBitmap != null && maskDrawable != null) {
                val roundedBitmap = ImageUtils.applyRoundedCornerMask(originalBitmap, maskDrawable)
                imageViewProduct.setImageBitmap(roundedBitmap)
            } else {
                Log.e("CoffeeAdapter", "Failed to load image for position: $position")
            }
        }
    }
}



