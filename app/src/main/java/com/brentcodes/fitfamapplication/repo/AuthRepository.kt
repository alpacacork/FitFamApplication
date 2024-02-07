package com.brentcodes.fitfamapplication.repo

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Response

interface AuthRepository {
    val currentUser: FirebaseUser?

    /*suspend fun firebaseSignUpWithEmailAndPassword(email: String, password: String): Response<AuthResult>
    suspend fun sendEmailVerification(): Response<AuthResult>
    suspend fun firebaseSignInWithEmailAndPassword(email: String, password: String): Response<AuthResult>
    suspend fun reloadFirebaseUser(): Response<AuthResult>
    suspend fun sendPasswordResetEmail(email: String): Response<Boolean>
    fun signOut()
    suspend fun revokeAccess(): Response<Boolean>
    fun getAuthState(viewModelScope: CoroutineScope): StateFlow<Boolean>*/


    fun loginUser(email: String, password: String): Flow<Response<AuthResult>>
    fun registerUser(email: String, password: String): Flow<Response<AuthResult>>
}