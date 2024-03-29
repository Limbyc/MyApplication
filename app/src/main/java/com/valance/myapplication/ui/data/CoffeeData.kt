package com.valance.myapplication.ui.data

import Coffee
import com.valance.myapplication.R

object CoffeeData {
    val productList = listOf(
        // Для Cappuccino
        Coffee("Cappuccino", 4.8, "Cappuccino", "with Chocolate", 4.53, R.drawable.coffee1, "Cappuccino",  1 , 220),
        Coffee("Cappuccino",4.9, "Cappuccino", "with Oat Milk", 3.90, R.drawable.coffee2, "Cappuccino",  2 , 110),
        Coffee("Cappuccino",4.5, "Cappuccino", "with Oat Milk", 4.50, R.drawable.coffee3, "Cappuccino", 3,100),
        Coffee("Cappuccino",4.0, "Cappuccino", "with Chocolate", 4.20, R.drawable.coffee4, "Cappuccino", 4,34),

        // Для Machiato
        Coffee("Machiato",4.7, "Machiato", "Classic", 4.25, R.drawable.coffee1, "Machiato", 5 ,48),
        Coffee("Machiato",4.5, "Machiato", "Caramel", 4.10,R.drawable.coffee2, "Machiato", 6 , 33),
        Coffee("Machiato",4.6, "Machiato", "Vanilla", 4.35,R.drawable.coffee3, "Machiato", 7, 201),
        Coffee("Machiato",4.8, "Machiato", "Hazelnut", 4.40, R.drawable.coffee4, "Machiato", 8,210),

        // Для Latte
        Coffee("Latte",4.5, "Latte", "Regular", 4.00, R.drawable.coffee1, "Latte", 9,120),
        Coffee("Latte",4.7, "Latte", "Vanilla", 4.20, R.drawable.coffee2, "Latte", 10,120),
        Coffee("Latte",4.3, "Latte", "Caramel", 3.90, R.drawable.coffee3, "Latte", 11,312),
        Coffee("Latte",4.6, "Latte", "Hazelnut", 4.10, R.drawable.coffee4, "Latte", 12,245),

        // Для Americano
        Coffee("Americano",4.2, "Americano", "Black", 3.80, R.drawable.coffee1, "Americano", 13,12),
        Coffee("Americano",4.4, "Americano", "Mild", 4.00, R.drawable.coffee2, "Americano", 14,19),
        Coffee("Americano",4.1, "Americano", "Bold", 3.70, R.drawable.coffee3, "Americano", 15,39),
        Coffee("Americano",4.3, "Americano", "Decaf", 3.90, R.drawable.coffee4, "Americano", 16,83),

        // Для Espresso
        Coffee("Espresso",4.6, "Espresso", "Single Shot", 3.50, R.drawable.coffee1, "Espresso", 17,63),
        Coffee("Espresso",4.8, "Espresso", "Double Shot", 4.00, R.drawable.coffee2, "Espresso", 18,92),
        Coffee("Espresso",4.4, "Espresso", "Macchiato", 3.80, R.drawable.coffee3, "Espresso", 19,62),
        Coffee("Espresso",4.5, "Espresso", "Caramel", 3.90, R.drawable.coffee4, "Espresso", 20,102),

        )
    fun getCoffeeById(coffeeId: Int): Coffee? {
        return productList.find { it.id == coffeeId }
    }

}