package com.brentcodes.fitfamapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.brentcodes.fitfamapplication.ui.screens.CustomScaffold
import com.brentcodes.fitfamapplication.ui.screens.Screen
import com.brentcodes.fitfamapplication.ui.screens.home.HomeScreen
import com.brentcodes.fitfamapplication.ui.screens.launch.LaunchScreen
import com.brentcodes.fitfamapplication.ui.screens.plan.PlanScreen
import com.brentcodes.fitfamapplication.ui.screens.profile.ProfileScreen
import com.brentcodes.fitfamapplication.ui.screens.signin.SignInScreen
import com.brentcodes.fitfamapplication.ui.screens.signup.SignUpScreen
import com.brentcodes.fitfamapplication.ui.screens.workout.WorkoutDetailsScreen
import com.brentcodes.fitfamapplication.ui.screens.workout.WorkoutScreen
import com.brentcodes.fitfamapplication.ui.theme.FitFamApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            FitFamApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    var bottomBar by remember { mutableStateOf(true) }

                    navController.addOnDestinationChangedListener { _, destination, _ ->
                        bottomBar = when (destination.route) {
                            Screen.AuthenticatedScreen.HomeScreen.route,
                            Screen.AuthenticatedScreen.PlanScreen.route,
                            Screen.AuthenticatedScreen.WorkoutScreen.WorkoutScreen.route,
                            Screen.AuthenticatedScreen.WorkoutScreen.WorkoutDetailsScreen.route,
                            Screen.AuthenticatedScreen.ProfileScreen.route -> true

                            else -> false
                        }
                    }

                    CustomScaffold(navController = navController, bottomBar = bottomBar) {
                        NavHost(
                            modifier = Modifier.padding(it),
                            navController = navController,
                            startDestination = Screen.SignUpScreen.route
                        ) {

                            //Launch Screen
                            composable(route = Screen.LaunchScreen.route) {
                                LaunchScreen()
                            }

                            //Sign Up Screen
                            composable(route = Screen.SignUpScreen.route) {
                                SignUpScreen(navController)
                            }

                            //Sign In Screen
                            composable(route = Screen.SignInScreen.route) {
                                SignInScreen(navController)
                            }

                            navigation(
                                route = Screen.AuthenticatedScreen.route,
                                startDestination = Screen.AuthenticatedScreen.HomeScreen.route
                            ) {
                                composable(route = Screen.AuthenticatedScreen.HomeScreen.route) {
                                    HomeScreen(navController = navController)
                                }
                                composable(route = Screen.AuthenticatedScreen.PlanScreen.route) {
                                    PlanScreen()
                                }

                                navigation(
                                    route = Screen.AuthenticatedScreen.WorkoutScreen.route,
                                    startDestination = Screen.AuthenticatedScreen.WorkoutScreen.WorkoutScreen.route
                                ) {
                                    composable(route = Screen.AuthenticatedScreen.WorkoutScreen.WorkoutScreen.route) {
                                        WorkoutScreen(navController)
                                    }
                                    composable(route = Screen.AuthenticatedScreen.WorkoutScreen.WorkoutDetailsScreen.route) {
                                        WorkoutDetailsScreen(navController = navController)
                                    }
                                }
                                composable(route = Screen.AuthenticatedScreen.ProfileScreen.route) {
                                    ProfileScreen()
                                }
                            }

                        }
                    }
                }
            }
        }
    }
}