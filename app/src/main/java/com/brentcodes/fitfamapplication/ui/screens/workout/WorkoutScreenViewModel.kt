package com.brentcodes.fitfamapplication.ui.screens.workout

import androidx.lifecycle.ViewModel
import com.brentcodes.fitfamapplication.repo.AuthRepository
import com.brentcodes.fitfamapplication.repo.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorkoutScreenViewModel @Inject constructor(
    val authRepository: AuthRepository,
    val dbRepository: DatabaseRepository
): ViewModel() {

}