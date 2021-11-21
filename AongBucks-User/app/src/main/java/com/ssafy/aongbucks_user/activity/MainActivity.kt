package com.ssafy.aongbucks_user.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ssafy.aongbucks_user.R
import com.ssafy.aongbucks_user.databinding.ActivityMainBinding
import com.ssafy.aongbucks_user.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    lateinit var navController  : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHostFragment.navController

        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)
    }

    fun hideBottomNav(state : Boolean) {
        if (state) binding.bottomNavigation.visibility = View.GONE
        else binding.bottomNavigation.visibility = View.VISIBLE
    }
}