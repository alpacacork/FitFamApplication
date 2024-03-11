package com.brentcodes.fitfamapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.brentcodes.fitfamapplication.ui.screens.Screen
import com.brentcodes.fitfamapplication.ui.screens.home.HomeScreen
import com.brentcodes.fitfamapplication.ui.screens.launch.LaunchScreen
import com.brentcodes.fitfamapplication.ui.screens.plan.PlanScreen
import com.brentcodes.fitfamapplication.ui.screens.profile.ProfileScreen
import com.brentcodes.fitfamapplication.ui.screens.signin.SignInScreen
import com.brentcodes.fitfamapplication.ui.screens.signup.SignUpScreen
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

                    NavHost(navController = navController, startDestination = Screen.SignUpScreen.route) {

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
            }
        }
    }
}