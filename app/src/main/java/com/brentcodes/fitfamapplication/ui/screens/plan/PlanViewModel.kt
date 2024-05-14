package com.brentcodes.fitfamapplication.ui.screens.plan

import androidx.lifecycle.ViewModel
import com.brentcodes.fitfamapplication.repo.AuthRepository
import com.brentcodes.fitfamapplication.repo.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlanViewModel @Inject constructor(
    val authRepository: AuthRepository,
    val dbRepository: DatabaseRepository
): ViewModel() {

}