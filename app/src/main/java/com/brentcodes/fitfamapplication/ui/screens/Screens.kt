package com.brentcodes.fitfamapplication.ui.screens

sealed class Screens (val route: String) {
    object LaunchScreen: Screens("launch")
    object HomeScreen: Screens("home")
    object ProfileScreen: Screens("profile")
    object PlanScreen: Screens("plan")
}
