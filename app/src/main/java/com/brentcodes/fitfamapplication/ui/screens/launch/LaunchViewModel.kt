package com.brentcodes.fitfamapplication.ui.screens.launch

import androidx.lifecycle.ViewModel
import com.brentcodes.fitfamapplication.repo.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LaunchViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
}