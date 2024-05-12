package com.brentcodes.fitfamapplication.ui.screens

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Sports
import androidx.compose.material.icons.filled.SportsGymnastics
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.brentcodes.fitfamapplication.ui.theme.DarkerGray
import com.brentcodes.fitfamapplication.ui.theme.RedAccent

@Preview
@Composable
fun BottomNavBar(navController: NavController = rememberNavController()) {
    BottomNavigation(
        backgroundColor = DarkerGray
    ) {

        var selectedRoute by remember { mutableStateOf(navController.currentDestination?.route) }
        navController.addOnDestinationChangedListener { _, destination, _ ->
            selectedRoute = destination.route
        }
        BottomNavigationItem(
            selected = selectedRoute == Screen.AuthenticatedScreen.HomeScreen.route,
            onClick = {
                navController.navigate(Screen.AuthenticatedScreen.HomeScreen.route)
            },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Home Icon",
                    modifier = Modifier.shadow(elevation = 5.dp)
                )
                   },
            label = { androidx.compose.material.Text("Home") },
            alwaysShowLabel = false,
            selectedContentColor = RedAccent,
            unselectedContentColor = Color.LightGray
        )

        BottomNavigationItem(
            selected = selectedRoute == Screen.AuthenticatedScreen.PlanScreen.route,
            onClick = {
                navController.navigate(Screen.AuthenticatedScreen.PlanScreen.route)
            },
            icon = {
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = "Plan Icon",
                    modifier = Modifier.shadow(elevation = 5.dp)
                )
            },
            label = { androidx.compose.material.Text("Plan") },
            alwaysShowLabel = false,
            selectedContentColor = RedAccent,
            unselectedContentColor = Color.LightGray
        )

        BottomNavigationItem(
            selected = (
                    (selectedRoute == Screen.AuthenticatedScreen.WorkoutScreen.WorkoutScreen.route)
                            or (selectedRoute == Screen.AuthenticatedScreen.WorkoutScreen.WorkoutDetailsScreen.route)
                    ),
            onClick = {
                navController.navigate(Screen.AuthenticatedScreen.WorkoutScreen.route)
            },
            icon = {
                Icon(
                    imageVector = Icons.Filled.SportsGymnastics,
                    contentDescription = "Workout Icon",
                    modifier = Modifier.shadow(elevation = 5.dp)
                )
            },
            label = { androidx.compose.material.Text("Workout") },
            alwaysShowLabel = false,
            selectedContentColor = RedAccent,
            unselectedContentColor = Color.LightGray
        )

        BottomNavigationItem(
            selected = selectedRoute == Screen.AuthenticatedScreen.ProfileScreen.route,
            onClick = {
                navController.navigate(Screen.AuthenticatedScreen.ProfileScreen.route)
            },
            icon = {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Profile Icon",
                    modifier = Modifier.shadow(elevation = 5.dp)
                )
                   },
            label = { androidx.compose.material.Text("Profile") },
            alwaysShowLabel = false,
            selectedContentColor = RedAccent,
            unselectedContentColor = Color.LightGray
        )
    }
}
