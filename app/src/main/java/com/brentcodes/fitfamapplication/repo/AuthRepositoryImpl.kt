package com.brentcodes.fitfamapplication.repo

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class AuthRepositoryImpl : AuthRepository {
    override val currentUser: FirebaseUser?
        get() = TODO("Not yet implemented")

    override fun loginUser(email: String, password: String): Flow<Response<AuthResult>> {
        TODO("Not yet implemented")
    }

    override fun registerUser(email: String, password: String): Flow<Response<AuthResult>> {
        TODO("Not yet implemented")
    }
}