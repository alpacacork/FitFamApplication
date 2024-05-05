package com.brentcodes.fitfamapplication.repo

import com.brentcodes.fitfamapplication.model.Exercise
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(): DatabaseRepository {
    override val db: FirebaseFirestore
        get() = Firebase.firestore

    override suspend fun addWorkout(exercises: List<Exercise>, name: String, desc: String) {
        TODO("Not yet implemented")
    }
}