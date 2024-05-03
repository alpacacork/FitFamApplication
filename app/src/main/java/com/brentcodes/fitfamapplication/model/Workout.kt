package com.brentcodes.fitfamapplication.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import java.time.LocalDateTime

data class ScheduledWorkout(
    val time: LocalDateTime,
    val id: Int
)

data class Workout(
    val id: Int,
    val name: String = "My Workout",
    val iconColor: Color = Color.Red,
    val icon : ImageVector = Icons.Filled.CheckBox,
    val exercises: List<Exercise> = emptyList()
)

data class Exercise(
    val exercise: String,
    val reps: Int,
    val sets: Int
)