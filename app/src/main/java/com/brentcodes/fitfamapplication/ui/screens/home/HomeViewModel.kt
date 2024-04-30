package com.brentcodes.fitfamapplication.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brentcodes.fitfamapplication.repo.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed class LoggedinState() {
    data class loggedIn(val user: String) : LoggedinState()
    object loggedout : LoggedinState()
    object loading : LoggedinState()
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    val authRepository: AuthRepository
) : ViewModel() {

    private val _userState = MutableStateFlow<LoggedinState>(LoggedinState.loading)
    val userState = _userState.stateIn(viewModelScope, SharingStarted.Lazily, LoggedinState.loading)

    init {
        viewModelScope.launch {
            delay(1000)
            _userState.value =
                if (authRepository.hasUser()) LoggedinState.loggedIn(authRepository.currentUserId) else LoggedinState.loggedout
        }


    }

}