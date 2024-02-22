package com.brentcodes.fitfamapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.brentcodes.fitfamapplication.ui.screens.TestScreen
import com.brentcodes.fitfamapplication.ui.screens.TestSignInScreen
import com.brentcodes.fitfamapplication.ui.screens.TestSignUpScreen
import com.brentcodes.fitfamapplication.ui.screens.signin.SignInScreen
import com.brentcodes.fitfamapplication.ui.screens.signup.SignUpScreen
import com.brentcodes.fitfamapplication.ui.theme.FitFamApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitFamApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SignUpScreen()
                }
            }
        }
    }
}