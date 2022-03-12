package com.oyamo.sinema.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.oyamo.sinema.R
import com.oyamo.sinema.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import io.ktor.client.engine.android.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigation()
    }

    // Set up bottom navigation
    private fun setupBottomNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.mainNavigationHost
        ) as NavHostFragment

        binding.bottomNavigation.setupWithNavController(navHostFragment.navController)
    }
}