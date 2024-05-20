package com.brentcodes.fitfamapplication.ui.screens.workout

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.brentcodes.fitfamapplication.R
import com.brentcodes.fitfamapplication.model.Workout
import com.brentcodes.fitfamapplication.ui.modifyColor
import com.brentcodes.fitfamapplication.ui.screens.Screen
import com.brentcodes.fitfamapplication.ui.screens.plan.PlanSchedulePopup
import com.brentcodes.fitfamapplication.ui.theme.BackgroundGray
import com.brentcodes.fitfamapplication.ui.theme.DarkerGray
import com.brentcodes.fitfamapplication.ui.theme.RedAccent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutScreen(navController: NavController) {
    val workouts = remember {
      listOf(
          Workout(1, "Arms", Color.Red),
          Workout(1, "Legs", Color.Blue),
          Workout(1, "Chest", Color.Green),
          Workout(1, "Biceps", Color.Gray),
          Workout(1, "Triceps", Color.Yellow),
          Workout(1, "Cardio", Color.Magenta),
          Workout(1, "Back", Color.Blue)
      )
    }
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(BackgroundGray)
    ) {
        FloatingActionButton(
            onClick = { showBottomSheet = true },
            containerColor = RedAccent,
            contentColor = Color.White,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(10.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add Workout Button",
                tint = Color.White
            )
        }
        //COME BACK TO THIS - BUTTON NOT AS RESPONSIVE AS PLAN SCREEN
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {showBottomSheet = false},
                sheetState = sheetState,
                containerColor = DarkerGray,
                contentColor = Color.White
            ) {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text("This is a test modal bottom sheet for adding workouts.")
                    Text("Add new workout", fontSize = 40.sp, fontWeight = FontWeight.Bold)
                    Text("Workout title:")

                }
            }
        }

        Column {
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
                    text = "Your Saved Workouts",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "View, edit and manage your saved workouts here!",
                    color = Color.LightGray,
                    fontSize = 14.sp
                )
            }
            LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.padding(top = 10.dp, start = 15.dp, end = 15.dp)) {
                items(workouts) {workout ->
                    WorkoutCard(workout = workout, onClick = {
                        navController.navigate(Screen.AuthenticatedScreen.WorkoutScreen.WorkoutDetailsScreen.route)
                        Log.d("nav", "navigating to workoutdetailscreen")
                    })
                }
            }
        }
    }
}

@Composable
fun WorkoutCard(modifier: Modifier = Modifier, workout: Workout = Workout(1, "Workout"), onClick: () -> Unit) {

    val durationId = "durationIcon"
    val durationText = buildAnnotatedString {
        appendInlineContent(durationId, "[icon]")
        append(" 30 Minutes")
    }
    val durationInlineContent = mapOf(Pair(durationId, InlineTextContent(
        Placeholder(
            width = 12.sp,
            height = 12.sp,
            placeholderVerticalAlign = PlaceholderVerticalAlign.Center
        )
    ) {
        Icon(imageVector = Icons.Filled.Timer, contentDescription = "Timer Icon", tint = Color.LightGray, modifier = Modifier.size(12.dp))
    }))

    val exercisesId = "exercisesIcon"
    val exercisesText = buildAnnotatedString {
        appendInlineContent(exercisesId, "[icon]")
        append(" 6 Exercises")
    }

    val exercisesInlineContent = mapOf(Pair(exercisesId, InlineTextContent(
        Placeholder(
            width = 12.sp,
            height = 12.sp,
            placeholderVerticalAlign = PlaceholderVerticalAlign.Center
        )
    ) {
        Icon(imageVector = Icons.AutoMirrored.Filled.List, contentDescription = "List Icon", tint = Color.LightGray, modifier = Modifier.size(12.dp))
    }))

    val gradient = remember {
        Brush.verticalGradient(
            colors = listOf(DarkerGray, Color.DarkGray),
            startY = 0F,
            endY = Float.POSITIVE_INFINITY
        )
    }

    Box(
        modifier = modifier
            .aspectRatio(1f)
            .padding(15.dp)
            .background(gradient, RoundedCornerShape(20.dp))
            .clickable(onClick = onClick)
    ){
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.muscle_24),
            tint = modifyColor(workout.iconColor, .6f, 0.7f),
            contentDescription = "Workout Icon",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(10.dp)
                .rotate(-30f)
                .size(70.dp)
        )
        Text(
            text = workout.name,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 20.dp, bottom = 40.dp),
            color = Color.White
        )
        Text(
            text = exercisesText,
            inlineContent = exercisesInlineContent,
            color = Color.LightGray,
            fontSize = 12.sp,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(20.dp)
        )
        Text(
            text = durationText,
            inlineContent = durationInlineContent,
            color = Color.LightGray,
            fontSize = 12.sp,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 20.dp, bottom = 5.dp)
        )
    }
}