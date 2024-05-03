package com.brentcodes.fitfamapplication.ui.screens.workout

import android.util.Log
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brentcodes.fitfamapplication.model.HomeScreenSectionCardModel
import com.brentcodes.fitfamapplication.ui.screens.home.HomeScreenSectionCard
import com.brentcodes.fitfamapplication.ui.theme.BackgroundGray
import com.brentcodes.fitfamapplication.ui.theme.DarkerGray
import com.brentcodes.fitfamapplication.ui.theme.RedAccent

@Preview
@Composable
fun WorkoutDetailsScreen(modifier: Modifier = Modifier) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(BackgroundGray)
    ) {
        Column {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 10.dp,
                        shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                    )
                    .background(DarkerGray)
                    .padding(10.dp)
            ) {
                Text(
                    text = "Arms",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Manage your workout here!",
                    color = Color.LightGray,
                    fontSize = 14.sp
                )
            }
            
            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)) {
                item { WorkoutDetailsSectionTitle(sectionTitle = "Exercises") }
                items(listOf("Bicep Curl", "Leg Press", "Dumbell Curl", "Pull Up", "Barbell Curl", "Push Ups")) {
                    ExerciseCard(title=it)
                }
            }
            
            
        }
    }
}

@Composable
fun WorkoutDetailsSectionTitle(
    sectionTitle: String,
    sectionSubtitle: String? = null
) {
    Column {
        Column(modifier = Modifier.padding(horizontal = 10.dp)) {
            androidx.compose.material.Text(
                text = "$sectionTitle>>",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 20.sp
            )
            if (sectionSubtitle != null) {
                androidx.compose.material.Text(text = "$sectionSubtitle>>", color = Color.LightGray)
            }
        }
    }
}

@Composable
fun ExerciseCard(
    modifier: Modifier = Modifier,
    expanded: Boolean = false,
    title: String = "",
    sets: Int = -1,
    reps: Int = -1,
    duration: Int = -1
) {
    var isExpanded = remember { mutableStateOf(expanded) }

    val background = if (isExpanded.value) RedAccent else DarkerGray
    val dropdownIcon: ImageVector = if (isExpanded.value) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowRight
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .background(background, RoundedCornerShape(20.dp))
        .clickable {
            isExpanded.value = !isExpanded.value
            Log.d("Exercise", "Clicked on Exercise: Expanded is $isExpanded.value")
        }
        .padding(10.dp)
    ) {
        Icon(imageVector = dropdownIcon, contentDescription = "Dropdown Icon", tint = Color.White, modifier = Modifier.align(Alignment.TopEnd))
        Column{
            Row{
                Text(title, fontWeight = FontWeight.Bold, color = Color.White)
            }
            Row{
                Text("x Sets, x Reps, x Minutes", color = Color.LightGray, fontSize = 12.sp)
            }
            if (isExpanded.value) Text("Hey this is expanded so show this text placeholder please! Thank you very much for doing this. xoxo.")
        }
    }

}