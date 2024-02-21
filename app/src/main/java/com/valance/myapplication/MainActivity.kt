package com.valance.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
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
            if (destination.id == R.id.startingFragment) {
                binding.bottomNav.visibility = View.GONE
            } else {
                binding.bottomNav.visibility = View.VISIBLE
            }
        }
    binding.bottomNav.setOnItemSelectedListener {
        when(it){
            R.id.home -> {
                navigateToMainFragment()
                true
            }
            R.id.heart -> {
                navigateToDetailFragment()
                true
            }
            R.id.bag -> {
                navigateToOrderFragment()
                true
            }
            R.id.notification -> {
//                navigateToNotificationFragment()
                true
            }
            else -> false
            }
        }
    }


    private fun navigateToMainFragment() {
        val navController = findNavController(R.id.my_nav_host_fragment)
        navController.navigate(R.id.action_startingFragment_to_mainFragment)
    }

    private fun navigateToDetailFragment() {
        val navController = findNavController(R.id.my_nav_host_fragment)
        navController.navigate(R.id.action_mainFragment_to_detailFragment)
    }

    private fun navigateToOrderFragment() {
        val navController = findNavController(R.id.my_nav_host_fragment)
        navController.navigate(R.id.action_mainFragment_to_orderFragment)
    }

//    private fun navigateToNotificationFragment() {
//        val navController = findNavController(R.id.my_nav_host_fragment)
//        navController.navigate(R.id.action_mainFragment_to_notificationFragment)
//    }
    private fun hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, binding.root).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

}