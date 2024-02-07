package com.brentcodes.fitfamapplication.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.brentcodes.fitfamapplication.ui.screens.LaunchScreen
import com.brentcodes.fitfamapplication.ui.screens.Screen

@Composable
fun FitFamApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.LaunchScreen.route) {
        composable(
            route = Screen.LaunchScreen.route
        ) {
            LaunchScreen()
        }
        composable(
            route = Screen.HomeScreen.route
        ) {
            //Home screen content goes here
        }
    }
}