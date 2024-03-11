package com.brentcodes.fitfamapplication.ui.screens.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brentcodes.fitfamapplication.repo.AuthRepository
import com.brentcodes.fitfamapplication.ui.screens.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    val authRepository: AuthRepository
) : ViewModel(){

    private val _email = MutableStateFlow("")
    val email = _email.stateIn(viewModelScope, SharingStarted.Lazily, "")

    private val _password = MutableStateFlow("")
    val password = _password.stateIn(viewModelScope, SharingStarted.Lazily, "")

    private val _confirmpassword = MutableStateFlow("")
    val confirmpassword = _confirmpassword.stateIn(viewModelScope, SharingStarted.Lazily, "")

    fun setEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun setPassword(newPassword: String) {
        _password.value = newPassword
    }

    fun setConfirmPassword(newPassword: String) {
        _confirmpassword.value = newPassword
    }

    fun signUp() {
        if (email.value == "") {
            Log.d("Signup", "Email Empty")
        } else if (password.value == "") {
            Log.d("Signup", "Password Empty")
        } else if (confirmpassword.value == "") {
            Log.d("Signup", "Confirm password Empty")
        } else if (password.value != confirmpassword.value) {
            Log.d("Signup", "Passwords do not match")
        } else {
            viewModelScope.launch {
                try {
                    authRepository.signUp(email.value, password.value)
                    if (authRepository.hasUser()) {
                        //navigate to home page with user content
                        Log.d("Signup", "User created: ${authRepository.currentUserId}")
                    }
                } catch (e: Exception) {
                    Log.d("Exception", e.message!!)
                }
            }
        }
    }
}