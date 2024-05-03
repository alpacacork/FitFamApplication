package com.brentcodes.fitfamapplication.ui.screens.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brentcodes.fitfamapplication.repo.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class loggedInState {
    loggedin, loggedout, error, invalidinput
}
@HiltViewModel
class SignInViewModel @Inject constructor(
    val authRepository: AuthRepository
): ViewModel() {

    private val _email = MutableStateFlow("")
    val email = _email.stateIn(viewModelScope, SharingStarted.Lazily, "")

    private val _password = MutableStateFlow("")
    val password = _password.stateIn(viewModelScope, SharingStarted.Lazily, "")

    private val _currentUser = MutableStateFlow(loggedInState.loggedout)
    val currentUser = _currentUser.stateIn(viewModelScope, SharingStarted.Lazily, loggedInState.loggedout)

    fun setEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun setPassword(newPassword: String) {
        _password.value = newPassword
    }

    fun signIn() {
        if (email.value.isEmpty() or password.value.isEmpty()) {
            _currentUser.value = loggedInState.invalidinput
        } else {
            viewModelScope.launch {
                try {
                    authRepository.signIn(email.value, password.value)
                    if (authRepository.hasUser()) {
                        _currentUser.value = loggedInState.loggedin
                    }
                } catch (e: Exception) {
                    _currentUser.value = loggedInState.error
                }
            }
        }
    }

    fun clearState() {
        _currentUser.value = loggedInState.loggedout
    }

}