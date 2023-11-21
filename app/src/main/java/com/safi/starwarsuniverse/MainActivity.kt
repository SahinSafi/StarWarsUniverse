package com.safi.starwarsuniverse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.safi.starwarsuniverse.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController

        /** BottomNavigationView setup with Navigation component */
        NavigationUI.setupWithNavController(navigationBarView = binding.bottomNav, navController = navController)
        navController.addOnDestinationChangedListener(this)
        binding.bottomNav.setOnItemSelectedListener {
            navController.popBackStack(R.id.characterFragment, false)
            navController.navigate(it.itemId)
            true
        }
    }

    /** Handling BottomNavigationView Visibility
     * Only for [CharacterFragment, StarshipFragment, PlanetFragment] BottomNav Will visible */
    override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {

        binding.bottomNav.isVisible = false

        when(destination.id){
            R.id.characterFragment-> binding.bottomNav.isVisible = true
            R.id.starshipFragment-> binding.bottomNav.isVisible = true
            R.id.planetFragment-> binding.bottomNav.isVisible = true
            else-> binding.bottomNav.isVisible = false
        }
    }

}