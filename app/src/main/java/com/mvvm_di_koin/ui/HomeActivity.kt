package com.mvvm_di_koin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.mvvm_di_koin.R
import kotlinx.android.synthetic.main.home_activity.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        initNavigation()
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.content ).navigateUp()
    }

    private fun initNavigation() {
        val navController = Navigation.findNavController(this, R.id.content)
        setupActionBarWithNavController( navController)
        NavigationUI.setupWithNavController(bottom_nav, navController)
    }

}
