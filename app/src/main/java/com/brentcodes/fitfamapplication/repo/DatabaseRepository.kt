package com.brentcodes.fitfamapplication.repo

import com.brentcodes.fitfamapplication.model.Exercise
import com.brentcodes.fitfamapplication.model.ScheduledWorkout
import com.brentcodes.fitfamapplication.model.Workout
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Date

interface DatabaseRepository {
    val db: FirebaseFirestore

    ////EXERCISE FUNCTIONS////
    suspend fun getExercises(workoutId: String): List<Exercise>
    suspend fun addExercise(name: String, reps: Int, sets: Int)

    suspend fun removeExercise(exerciseId: String)

    suspend fun editExercise(exerciseId: String, name: String, reps: Int, sets: Int)


    ////WORKOUT FUNCTIONS////
    suspend fun getWorkouts(): List<Workout>
    suspend fun addWorkout(exercises: List<Exercise>, name: String, desc: String)

    suspend fun removeWorkout(workoutId: String)

    suspend fun editWorkout(workoutId: String, exercises: List<Exercise>, name: String, desc: String)



    ////SCHEDULED WORKOUT FUNCTIONS////
    suspend fun getScheduledWorkouts(): List<ScheduledWorkout>
    suspend fun scheduleWorkout(workoutId: String, date: Date)

    suspend fun removeScheduledWorkout(scheduledWorkoutId: String)

    suspend fun editScheduledWorkout(scheduledWorkoutId: String, workoutId: String, date: Date)

}