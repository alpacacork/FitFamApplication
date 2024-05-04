package com.brentcodes.fitfamapplication.repo

import android.util.Log
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.app
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(): AuthRepository {

    override val currentUser: Flow<FirebaseUser?>
        get() = callbackFlow {
            Firebase.auth.currentUser
        }

    override val currentUserId: String
        get() = Firebase.auth.currentUser?.uid.orEmpty()

    override val db: FirebaseFirestore
        get() = Firebase.firestore

    override fun hasUser(): Boolean {
        return Firebase.auth.currentUser != null
    }

    override suspend fun signIn(email: String, password: String) {
        Firebase.auth.signInWithEmailAndPassword(email, password).await()
        Log.d("SignIn", "Signing in with $email and $password")
        val user = currentUserId
        Log.d("SignIn", "Current user: $user")

        val data = mapOf("name" to "Brent Cenci", "level" to 12)
        if (user.isNotEmpty()) {
            //do something to generate user file?
            Log.d("SignIn", "Attempting to create db collection with db : ${db.collection("users").path}")
            db.collection("users").document(user).set(data)
        }

    }

    override suspend fun signUp(email: String, password: String) {
        Firebase.auth.createUserWithEmailAndPassword(email, password).await()
    }

    override suspend fun signOut() {
        Firebase.auth.signOut()
    }

    override suspend fun deleteAccount() {
        Firebase.auth.currentUser!!.delete().await()
    }



}