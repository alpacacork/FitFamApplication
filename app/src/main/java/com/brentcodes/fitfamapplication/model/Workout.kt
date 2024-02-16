package com.brentcodes.fitfamapplication.model

import java.time.LocalDateTime

data class ScheduledWorkout(
    val time: LocalDateTime,
    val id: Int
)

data class Workout(
    val id: Int,
    val desc: String = "",
    val exercises: List<Exercise>
)

data class Exercise(
    val exercise: String,
    val reps: Int,
    val sets: Int
)