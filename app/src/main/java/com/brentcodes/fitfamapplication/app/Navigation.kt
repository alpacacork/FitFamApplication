package com.brentcodes.fitfamapplication.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.brentcodes.fitfamapplication.ui.screens.LaunchScreen
import com.brentcodes.fitfamapplication.ui.screens.Screens

@Composable
fun FitFamApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.LaunchScreen.route) {
        composable(
            route = Screens.LaunchScreen.route
        ) {
            LaunchScreen()
        }
        composable(
            route = Screens.HomeScreen.route
        ) {
            //Home screen content goes here
        }
    }
}