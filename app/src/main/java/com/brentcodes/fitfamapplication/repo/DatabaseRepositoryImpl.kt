package com.brentcodes.fitfamapplication.repo

import com.brentcodes.fitfamapplication.model.Exercise
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import java.util.Date
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(): DatabaseRepository {
    override val db: FirebaseFirestore
        get() = Firebase.firestore

    override suspend fun addExercise(name: String, reps: Int, sets: Int) {
        val data = mapOf("name" to name, "reps" to reps, "sets" to sets)
        val doc = db.collection("exercises").document()
        doc.set(data)
        //NEED TO PASS ID TO LOGGED IN USER
    }

    override suspend fun removeExercise(exerciseId: String) {
        db.collection("exercises").document(exerciseId).delete()
        //ALSO NEED TO CONSIDER IF THE EXERCISE IS IN A WORKOUT?
    }

    override suspend fun editExercise(exerciseId: String, name: String, reps: Int, sets: Int) {
        val data = mapOf("name" to name, "reps" to reps, "sets" to sets)
        db.collection("exercises").document(exerciseId).set(data)
    }

    override suspend fun addWorkout(exercises: List<Exercise>, name: String, desc: String) {
        val data = mapOf("exercises" to exercises, "name" to name, "desc" to desc)
        db.collection("workouts").document().set(data)
    }

    override suspend fun removeWorkout(workoutId: String) {
        db.collection("workouts").document(workoutId).delete()
    }

    override suspend fun editWorkout(
        workoutId: String,
        exercises: List<Exercise>,
        name: String,
        desc: String
    ) {
        val data = mapOf("exercises" to exercises, "name" to name, "desc" to desc)
        db.collection("workouts").document(workoutId).set(data)
    }

    override suspend fun scheduleWorkout(workoutId: String, date: Date) {
        TODO("Not yet implemented")
    }

    override suspend fun removeScheduledWorkout(scheduledWorkoutId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun editScheduledWorkout(
        scheduledWorkoutId: String,
        workoutId: String,
        date: Date
    ) {
        TODO("Not yet implemented")
    }
}