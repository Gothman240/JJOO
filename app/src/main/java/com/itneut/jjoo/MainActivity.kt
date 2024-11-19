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

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController

        // navegación
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.menu.findItem(R.id.nav_home).setIcon(R.drawable.ic_home_fill)
        bottomNavigationView.setOnItemSelectedListener { item ->
            bottomNavigationView.menu.findItem(R.id.nav_home).setIcon(R.drawable.ic_home)
            bottomNavigationView.menu.findItem(R.id.nav_medals).setIcon(R.drawable.ic_medal)
            bottomNavigationView.menu.findItem(R.id.nav_profile).setIcon(R.drawable.ic_profile)
            when (item.itemId) {
                R.id.nav_home -> {
                    item.setIcon(R.drawable.ic_home_fill)
                    navController.navigate(R.id.eventFragment)
                    true
                }

                R.id.nav_medals -> {
                    item.setIcon(R.drawable.ic_medal_fill)
                    navController.navigate(R.id.medalFragment)
                    true
                }

                R.id.nav_profile -> {
                    item.setIcon(R.drawable.ic_profile_fill)
                    navController.navigate(R.id.profileFragment)
                    true
                }

                else -> false
            }
        }

    }

}