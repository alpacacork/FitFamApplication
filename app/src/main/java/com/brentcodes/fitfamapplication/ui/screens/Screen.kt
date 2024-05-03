package com.brentcodes.fitfamapplication.ui.screens

sealed class Screen (val route: String) {
    object LaunchScreen: Screen("LAUNCH_SCREEN")
    object SignInScreen: Screen("SIGNIN_SCREEN")
    object SignUpScreen: Screen("SIGNUP_SCREEN")
    object AuthenticatedScreen : Screen("AUTHENTICATED_SCREEN") {
        object HomeScreen: Screen("HOME_SCREEN")
        object ProfileScreen: Screen("PROFILE_SCREEN")
        object WorkoutScreens: Screen("WORKOUT_SCREENS") {
            object WorkoutScreen: Screen("WORKOUT_SCREEN")
            object WorkoutDetailsScreen: Screen("WORKOUT_DETAILS_SCREEN")
        }
        object PlanScreen: Screen("PLAN_SCREEN")

    }

}
