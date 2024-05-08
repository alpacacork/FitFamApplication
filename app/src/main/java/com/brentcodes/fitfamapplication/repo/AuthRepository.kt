package com.brentcodes.fitfamapplication.repo

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Response
import java.time.ZonedDateTime

interface AuthRepository {
    val currentUser: FirebaseUser?
    val currentUserId: String
    val db: FirebaseFirestore
    fun hasUser(): Boolean
    suspend fun getUserJoinLong(): Long
    suspend fun signIn(email: String, password: String)
    suspend fun signUp(email: String, password: String)
    suspend fun signOut()
    suspend fun deleteAccount()
}