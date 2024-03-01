package com.valance.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.valance.myapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideSystemUI()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.startingFragment, R.id.detailFragment -> {
                    binding.bottomNav.visibility = View.GONE
                }
                else -> {
                    binding.bottomNav.visibility = View.VISIBLE
                }
            }
        }

        binding.bottomNav.setItemSelected(R.id.home)
        binding.bottomNav.setOnItemSelectedListener {
            when (it) {
                R.id.home -> {
                    if (navController.currentDestination?.id != R.id.mainFragment) {
                        navController.navigate(R.id.mainFragment)
                    }
                }

                R.id.heart -> {
                    if (navController.currentDestination?.id != R.id.likeFragment) {
                        navController.navigate(R.id.likeFragment)
                    }
                }

                R.id.bag -> {
                    if (navController.currentDestination?.id != R.id.orderFragment) {
                        navController.navigate(R.id.orderFragment)
                    }
                }

                R.id.notification -> {
                    // navigateToNotificationFragment()
                }

                else -> false
            }
        }

    }


    private fun hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, binding.root).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

}