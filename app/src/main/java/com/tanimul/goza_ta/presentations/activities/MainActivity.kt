package com.tanimul.goza_ta.presentations.activities

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tanimul.goza_ta.R
import com.tanimul.goza_ta.base.BaseActivity
import com.tanimul.goza_ta.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun init(savedInstanceState: Bundle?) {

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_hostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)

        val fragmentsWithBottomNav = setOf(
            R.id.homeFragment,
            R.id.bookmarkFragment,
            R.id.notificationFragment,
            R.id.profileFragment,
        )

        // Hide/show the BottomNavigationView
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id in fragmentsWithBottomNav) {
                binding.bottomNavigationView.visibility = View.VISIBLE
            } else {
                binding.bottomNavigationView.visibility = View.GONE
            }
        }

    }

}