package com.valance.myapplication.ui.data

import com.valance.myapplication.R

object CoffeeData {
    val productList = listOf(
        // Для Cappuccino
        Coffee(4.8, "Cappuccino", "with Chocolate", 4.53, R.drawable.coffee1, "Cappuccino"),
        Coffee(4.9, "Cappuccino", "with Oat Milk", 3.90, R.drawable.coffee2, "Cappuccino"),
        Coffee(4.5, "Cappuccino", "with Oat Milk", 4.50, R.drawable.coffee3, "Cappuccino"),
        Coffee(4.0, "Cappuccino", "with Chocolate", 4.20, R.drawable.coffee4, "Cappuccino"),

        // Для Machiato
        Coffee(4.7, "Machiato", "Classic", 4.25, R.drawable.coffee1, "Machiato"),
        Coffee(4.5, "Machiato", "Caramel", 4.10,R.drawable.coffee2, "Machiato"),
        Coffee(4.6, "Machiato", "Vanilla", 4.35,R.drawable.coffee3, "Machiato"),
        Coffee(4.8, "Machiato", "Hazelnut", 4.40, R.drawable.coffee4, "Machiato"),

        // Для Latte
        Coffee(4.5, "Latte", "Regular", 4.00, R.drawable.coffee1, "Latte"),
        Coffee(4.7, "Latte", "Vanilla", 4.20, R.drawable.coffee2, "Latte"),
        Coffee(4.3, "Latte", "Caramel", 3.90, R.drawable.coffee3, "Latte"),
        Coffee(4.6, "Latte", "Hazelnut", 4.10, R.drawable.coffee4, "Latte"),

        // Для Americano
        Coffee(4.2, "Americano", "Black", 3.80, R.drawable.coffee1, "Americano"),
        Coffee(4.4, "Americano", "Mild", 4.00, R.drawable.coffee2, "Americano"),
        Coffee(4.1, "Americano", "Bold", 3.70, R.drawable.coffee3, "Americano"),
        Coffee(4.3, "Americano", "Decaf", 3.90, R.drawable.coffee4, "Americano"),

        // Для Espresso
        Coffee(4.6, "Espresso", "Single Shot", 3.50, R.drawable.coffee1, "Espresso"),
        Coffee(4.8, "Espresso", "Double Shot", 4.00, R.drawable.coffee2, "Espresso"),
        Coffee(4.4, "Espresso", "Macchiato", 3.80, R.drawable.coffee3, "Espresso"),
        Coffee(4.5, "Espresso", "Caramel", 3.90, R.drawable.coffee4, "Espresso"),

        )
}