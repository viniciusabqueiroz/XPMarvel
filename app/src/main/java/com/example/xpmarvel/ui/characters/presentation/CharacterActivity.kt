package com.example.xpmarvel.ui.characters.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.xpmarvel.R
import com.example.xpmarvel.databinding.ActivityCharacterBinding

class CharacterActivity : AppCompatActivity() {

    lateinit var viewDataBinding: ActivityCharacterBinding

    private lateinit var navHost: NavHostFragment
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_character)

        navHost = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return
        navController = navHost.navController

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment)
        )
        setupAppBar()
    }

    private fun setupAppBar() {
        val toolbar = viewDataBinding.toolbar
        setSupportActionBar(toolbar)
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }
}