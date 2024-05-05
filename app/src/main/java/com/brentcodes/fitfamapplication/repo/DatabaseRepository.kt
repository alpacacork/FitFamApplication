package com.brentcodes.fitfamapplication.repo

import com.brentcodes.fitfamapplication.model.Exercise
import com.google.firebase.firestore.FirebaseFirestore

interface DatabaseRepository {
    val db: FirebaseFirestore
    suspend fun addWorkout(exercises: List<Exercise>, name: String, desc: String)
}