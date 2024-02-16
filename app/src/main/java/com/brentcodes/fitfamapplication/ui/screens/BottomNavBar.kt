package com.brentcodes.fitfamapplication.ui.screens

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.brentcodes.fitfamapplication.ui.theme.DarkerGray
import com.brentcodes.fitfamapplication.ui.theme.RedAccent

@Composable
fun BottomNavBar() {
    @Composable
    fun RecipeBottomNavBar(navController: NavController) {
        BottomNavigation(
            backgroundColor = DarkerGray
        ) {

            var selectedRoute by remember { mutableStateOf(navController.currentDestination?.route) }
            navController.addOnDestinationChangedListener { _, destination, _ ->
                selectedRoute = destination.route
            }
            BottomNavigationItem(
                selected = selectedRoute == Screen.HomeScreen.route,
                onClick = {
                    navController.navigate(Screen.HomeScreen.route)
                },
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = "Home Icon",
                        modifier = Modifier.shadow(elevation = 5.dp)
                    )
                       },
                label = { androidx.compose.material.Text("Home") },
                selectedContentColor = RedAccent,
                unselectedContentColor = Color.LightGray
            )

            BottomNavigationItem(
                selected = selectedRoute == Screen.PlanScreen.route,
                onClick = {
                    navController.navigate(Screen.PlanScreen.route)
                },
                icon = {
                    Icon(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = "Plan Icon",
                        modifier = Modifier.shadow(elevation = 5.dp)
                    )
                },
                label = { androidx.compose.material.Text("Plan") },
                selectedContentColor = RedAccent,
                unselectedContentColor = Color.LightGray
            )

            BottomNavigationItem(
                selected = selectedRoute == Screen.ProfileScreen.route,
                onClick = {
                    navController.navigate(Screen.ProfileScreen.route)
                },
                icon = {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "Profile Icon",
                        modifier = Modifier.shadow(elevation = 5.dp)
                    )
                       },
                label = { androidx.compose.material.Text("Profile") },
                selectedContentColor = RedAccent,
                unselectedContentColor = Color.LightGray
            )
        }
    }
}
