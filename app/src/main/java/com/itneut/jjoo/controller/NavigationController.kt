package com.itneut.jjoo.controller

import android.widget.Toast
import com.itneut.jjoo.R
import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.itneut.jjoo.repositories.UserRepository

class NavigationController(
    private val navController: NavController,
    private val bottomNavigationView: BottomNavigationView
) {

    fun setupNavigation() {
        bottomNavigationView.setOnItemSelectedListener { item ->
            resetIcons()
            setSelectedIcon(item.itemId)

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
                    if (UserRepository.isLoggedIn()) {
                        navController.navigate(R.id.profileFragment)
                        true
                    } else {
                        navController.navigate(R.id.logInFragment)
                        false
                    }
                }
                else -> false
            }
        }
        setSelectedIcon(R.id.nav_home)
    }

    private fun resetIcons() {
        bottomNavigationView.menu.findItem(R.id.nav_home).setIcon(R.drawable.ic_home)
        bottomNavigationView.menu.findItem(R.id.nav_medals).setIcon(R.drawable.ic_medal)
        bottomNavigationView.menu.findItem(R.id.nav_profile).setIcon(R.drawable.ic_profile)
    }

    private fun setSelectedIcon(itemId: Int) {
        when (itemId) {
            R.id.nav_home -> bottomNavigationView.menu.findItem(R.id.nav_home).setIcon(R.drawable.ic_home_fill)
            R.id.nav_medals -> bottomNavigationView.menu.findItem(R.id.nav_medals).setIcon(R.drawable.ic_medal_fill)
            R.id.nav_profile -> bottomNavigationView.menu.findItem(R.id.nav_profile).setIcon(R.drawable.ic_profile_fill)
        }
    }

    private fun isLoggedIn(): Boolean {
        // TODO
        return false
    }
}
