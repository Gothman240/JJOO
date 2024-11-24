package com.itneut.jjoo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.itneut.jjoo.controller.NavigationController
import com.itneut.jjoo.repositories.EventRepository
import com.itneut.jjoo.ui.event.EventAdapter
import com.itneut.jjoo.ui.event.EventFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var navigationController: NavigationController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Encuentra el NavController y BottomNavigationView
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Configura el controlador de navegación
        navigationController = NavigationController(navController, bottomNavigationView)
        navigationController.setupNavigation()

    }

}