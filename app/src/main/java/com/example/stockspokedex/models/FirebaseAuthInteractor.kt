package com.example.stockspokedex.models

import com.google.firebase.auth.FirebaseUser

interface FirebaseAuthInteractor {
    suspend fun authWithGoogle(idToken: String): FirebaseUser?
}