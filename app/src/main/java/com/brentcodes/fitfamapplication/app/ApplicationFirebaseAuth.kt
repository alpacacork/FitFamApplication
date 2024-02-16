package com.brentcodes.fitfamapplication.app

import android.app.Application
import com.google.firebase.auth.FirebaseAuth

class ApplicationFirebaseAuth : Application() {
    val auth = FirebaseAuth.getInstance()
}