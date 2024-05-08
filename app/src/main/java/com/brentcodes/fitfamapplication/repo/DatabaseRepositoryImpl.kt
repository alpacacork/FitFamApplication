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

    override suspend fun addExercise(exercises: List<Exercise>, name: String, desc: String) {
        val data = mapOf("exercises" to exercises, "name" to name, "desc" to desc)
        db.collection("exercises").document().set(data)
    }

    override suspend fun removeExercise(workoutId: String) {
        db.collection("exercises").document(workoutId).delete()
    }

    override suspend fun editExercise(
        workoutId: String,
        exercises: List<Exercise>,
        name: String,
        desc: String
    ) {
        val data = mapOf("exercises" to exercises, "name" to name, "desc" to desc)
        db.collection("exercises").document(workoutId).set(data)
    }

    override suspend fun addWorkout(exercises: List<Exercise>, name: String, desc: String) {
        TODO("Not yet implemented")
    }

    override suspend fun removeWorkout(workoutId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun editWorkout(
        workoutId: String,
        exercises: List<Exercise>,
        name: String,
        desc: String
    ) {
        TODO("Not yet implemented")
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