package com.brentcodes.fitfamapplication.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.brentcodes.fitfamapplication.ui.screens.launch.LaunchScreen
import com.brentcodes.fitfamapplication.ui.screens.Screen
import com.brentcodes.fitfamapplication.ui.screens.home.HomeScreen
import com.brentcodes.fitfamapplication.ui.screens.plan.PlanScreen
import com.brentcodes.fitfamapplication.ui.screens.profile.ProfileScreen
import com.brentcodes.fitfamapplication.ui.screens.signin.SignInScreen
import com.brentcodes.fitfamapplication.ui.screens.signup.SignUpScreen

@Composable
fun FitFamApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.LaunchScreen.route) {

        //Launch Screen
        composable(route = Screen.LaunchScreen.route) {
            LaunchScreen()
        }

        //Sign Up Screen
        composable(route = Screen.SignUpScreen.route) {
            SignUpScreen()
        }

        //Sign In Screen
        composable(route = Screen.SignInScreen.route) {
            SignInScreen()
        }

        //Home Screen
        composable(route = Screen.HomeScreen.route) {
            HomeScreen()
        }

        //Plan Screen
        composable(route = Screen.PlanScreen.route) {
            PlanScreen()
        }

        //Profile Screen
        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen()
        }
    }
}