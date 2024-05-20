package com.brentcodes.fitfamapplication.ui.screens.profile

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Logout
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.brentcodes.fitfamapplication.ui.screens.Screen
import com.brentcodes.fitfamapplication.ui.theme.BackgroundGray
import com.brentcodes.fitfamapplication.ui.theme.DarkerGray
import com.brentcodes.fitfamapplication.ui.theme.RedAccent
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.CartesianChartHost
import com.patrykandpatrick.vico.compose.chart.layer.rememberColumnCartesianLayer
import com.patrykandpatrick.vico.compose.chart.rememberCartesianChart
import com.patrykandpatrick.vico.core.model.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.model.columnSeries
import io.grpc.Context
import java.sql.Timestamp
import java.time.Instant
import java.time.Month
import java.time.YearMonth
import java.time.ZoneId
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    //STATISTICS
    //RECENT WORKOUTS

    val name = "Brent Cenci"
    val monthJoinedTimestamp = Firebase.auth.currentUser?.metadata?.creationTimestamp
    val joined = Instant.ofEpochMilli(monthJoinedTimestamp?: 1).atZone(ZoneId.systemDefault())
    val context = LocalContext.current


    Box(
        Modifier
            .fillMaxSize()
            .background(BackgroundGray)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                )
                .background(DarkerGray)
                .padding(10.dp)
            ) {
                Text(
                    text = name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "User since ${joined.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH)}",
                    color = Color.White
                )
            }
            val modelProducer = remember { CartesianChartModelProducer.build() }
            LaunchedEffect(key1 = Unit) { modelProducer.tryRunTransaction { columnSeries { series(4, 12, 8, 16) } } }
            CartesianChartHost(
                rememberCartesianChart(
                    rememberColumnCartesianLayer(),
                    startAxis = rememberStartAxis(),
                    bottomAxis = rememberBottomAxis(),
                ),
                modelProducer,
            )
            Button(
                onClick = {
                    viewModel.signOut()
                    navController.navigate(Screen.SignUpScreen.route)
                },
                colors= ButtonColors(RedAccent, Color.White, RedAccent, Color.White)
            ) {
                Icon(imageVector = Icons.AutoMirrored.Rounded.Logout, contentDescription = "Logout Icon", tint = Color.White)
                Text("Sign Out")
            }
        }
    }
}