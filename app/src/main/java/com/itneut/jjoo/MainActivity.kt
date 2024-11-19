package com.itneut.jjoo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.itneut.jjoo.repositories.EventRepository
import com.itneut.jjoo.ui.event.EventAdapter
import com.itneut.jjoo.ui.event.EventFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Encuentra el NavHostFragment y el NavController
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController

// Configura la lógica personalizada en el BottomNavigationView
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    navController.navigate(R.id.eventFragment)
                    true
                }
                R.id.nav_medals -> {
                    navController.navigate(R.id.medalFragment)
                    true
                }
                R.id.nav_profile -> {
                        navController.navigate(R.id.profileFragment)
                        true
                }
                else -> false
            }
        }

    }

}