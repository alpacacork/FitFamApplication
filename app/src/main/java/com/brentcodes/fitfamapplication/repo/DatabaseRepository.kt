package com.brentcodes.fitfamapplication.repo

import com.brentcodes.fitfamapplication.model.Exercise
import com.google.firebase.firestore.FirebaseFirestore

interface DatabaseRepository {
    val db: FirebaseFirestore

    ////EXERCISE FUNCTIONS////
    //suspend fun addExercise(exercises: List<Exercise>, name: String, desc: String)

    //suspend fun removeExercise(workoutId: String)

    //suspend fun editExercise(workoutId: String, exercises: List<Exercise>, name: String, desc: String)


    ////WORKOUT FUNCTIONS////
    suspend fun addWorkout(exercises: List<Exercise>, name: String, desc: String)

    //suspend fun removeWorkout(workoutId: String)

    //suspend fun editWorkout(workoutId: String, exercises: List<Exercise>, name: String, desc: String)



    ////SCHEDULED WORKOUT FUNCTIONS////


}